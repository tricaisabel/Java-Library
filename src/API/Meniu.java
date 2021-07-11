package API;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Meniu {
    static List<Carte> carti=null;

    public static List<Object> afisareMeniu(){
        carti=ReadFile.citireCarti("carti.txt");
        Scanner in = new Scanner(System.in);
        System.out.println("\nActiuni:");
        System.out.println("A) Verificarea disponibilitatii tuturor cartilor");
        System.out.println("B) Imprumutul unei carti");
        System.out.println("C) Returnarea unei carti");
        System.out.println("D) Iesire");

        System.out.print("Cod actiunea (A,B, C sau D): ");
        String alegereActiune = in.nextLine();

        String variante="ABCD";
        while(!variante.contains(alegereActiune)||alegereActiune.length()!=1)
        {
            System.out.print("Cod actiunea invalid. Alegeti dintre (A,B, C sau D): ");
            alegereActiune = in.nextLine();
        }

        Carte carte;
        if(alegereActiune.equals("B") || alegereActiune.equals("C")){
            System.out.println("\nCarti disponibile:");
            int index = 1;
            for(Carte c:carti){
                System.out.println(index+") "+c.toString2());
                index++;
            }
            System.out.print("Index carte : ");
            String alegereCarte = in.nextLine();

            variante="123";
            while(!variante.contains(alegereCarte)||alegereCarte.length()!=1)
            {
                System.out.print("Cod carte invalid. Alegeti din intervalul [1,"+carti.size()+"] : ");
                alegereCarte = in.nextLine();
            }

            carte=carti.get(Integer.parseInt(alegereCarte)-1);
        }
        else carte=carti.get(0);

        tipCerere tip=null;
        if(alegereActiune.equals("A")) tip=tipCerere.disponibilitate;
        else if(alegereActiune.equals("B")) tip=tipCerere.imprumut;
        else if(alegereActiune.equals("C")) tip=tipCerere.retur;
        else if(alegereActiune.equals("D")) tip=tipCerere.exit;
        return Arrays.asList(carte,tip);
    }

}
