package Server_Client;

import API.*;
import BibliotecaJDBC.Biblioteca;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import static BibliotecaJDBC.Biblioteca.*;

public class Server {

    Biblioteca biblioteca=new Biblioteca();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Carte> carti=ReadFile.citireCarti("carti.txt");
        createConnection();
        dropTable();
        createTable();
        for(Carte c : carti){
            insertCarte(c);
        }
        while (true) {

            ServerSocket ss = new ServerSocket(7777);
            System.out.println("ServerSocket awaiting connections...");
            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + socket + "!");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            Cerere cerere = (Cerere) objectInputStream.readObject();
            System.out.println("Cerere primita:");
            System.out.println(cerere);

            if(cerere.getTip().equals(tipCerere.exit)){
                System.out.println("Inchidere socket.");
                ss.close();
                socket.close();
                break;
            }

            Raspuns raspuns = evaluareCerere(cerere);
            objectOutputStream.writeObject(raspuns);
            System.out.println("Inchidere socket.");
            ss.close();
            socket.close();
        }
    }

    private static Raspuns evaluareCerere(Cerere cerere)
    {
        List<Object> evaluare = null;
        tipCerere tip1=cerere.getTip();
        Carte carte=cerere.getCarte();
        if(tip1==tipCerere.disponibilitate) evaluare=verificaDisponibilitate(carte);
        else if(tip1==tipCerere.imprumut) evaluare=imprumut(carte);
        else if(tip1==tipCerere.retur) evaluare=retur(carte);

        tipRaspuns tip2=(tipRaspuns)evaluare.get(0);
        List<Carte> carti=(List<Carte>)evaluare.get(1);
        return new Raspuns(tip2,carti);
    }

    private static List<Object> verificaDisponibilitate(Carte carte){
        List<Carte> carti=selectCarti();
        tipRaspuns tip=tipRaspuns.R11;
        return Arrays.asList(tip,carti);
    }

    private static List<Object> imprumut(Carte carte){
        List<Carte> carti=selectCarti();
        tipRaspuns tip=null;
        boolean gasit= false;
        for( Carte c : carti){
            if(c.getAutori().equals(carte.getAutori()) && carte.getTitlu().equals(c.getTitlu()) && Integer.compare(carte.getAn(),c.getAn())==0) {
                gasit=true;
                int exemplare=c.getExemplare();
                if (exemplare-1>=0) {
                    tip = tipRaspuns.R23;
                    updateCarte(carte,"-1");
                    c.setExemplare(c.getExemplare()-1);
                }
                else{
                    tip=tipRaspuns.R22;
                }
            }
        }
        if(!gasit) tip=tipRaspuns.R21;
        return Arrays.asList(tip,carti);
    }

    private static List<Object> retur(Carte carte){
        List<Carte> carti=selectCarti();
        tipRaspuns tip=null;
        for( Carte c : carti){
            if(c.getAutori().equals(carte.getAutori()) && carte.getTitlu().equals(c.getTitlu()) && Integer.compare(carte.getAn(),c.getAn())==0) {
                tip = tipRaspuns.R31;
                updateCarte(carte,"+1");
            }
        }
        return Arrays.asList(tip,carti);
    }
}
