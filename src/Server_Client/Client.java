package Server_Client;
import API.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        while(true) {
            Socket socket = new Socket("localhost", 7777);
            System.out.println("Connectat!");

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<Object> parametrii = Meniu.afisareMeniu();
            Carte carte = (Carte) parametrii.get(0);
            tipCerere tip = (tipCerere) parametrii.get(1);
            Cerere cerere = new Cerere(tip, carte);
            objectOutputStream.writeObject(cerere);

            if (cerere.getTip().equals(tipCerere.exit)) {
                System.out.println("Inchidere socket si terminare proces");
                socket.close();
                break;
            } else {
                System.out.println("S-a primit urmatorul raspuns de la server:");
                Raspuns raspuns = (Raspuns) objectInputStream.readObject();
                if (cerere.getTip().equals(tipCerere.disponibilitate)) {
                    for (Carte c : raspuns.getCarti()) {
                        System.out.println(c.toString());
                    }
                } else if (cerere.getTip().equals(tipCerere.imprumut)) {
                    if (raspuns.getTip().equals(tipRaspuns.R21)) {
                        System.out.println("Titlul cautat nu exista in biblioteca!");
                    }
                    if (raspuns.getTip().equals(tipRaspuns.R22)) {
                        System.out.println("Titlul cautat nu are niciun exemplar disponibil!");
                    }
                    if (raspuns.getTip().equals(tipRaspuns.R23)) {
                        System.out.println("Imprumutul a fost realizat!");
                    }
                } else if (cerere.getTip().equals(tipCerere.retur)) {
                    System.out.println("Cartea a fost returnata cu succes!");
                }
            }
        }
    }
}

