package API;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

public class ReadFile {
    public static List<Carte> citireCarti(String filename) {
        List<Carte> carti= new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String cota = myReader.nextLine();
                String titlu = myReader.nextLine();
                String autori = myReader.nextLine();
                int an = Integer.parseInt(myReader.nextLine());
                int exemplare = Integer.parseInt(myReader.nextLine());
                carti.add(new Carte(cota,titlu,autori,an,exemplare));
            }
            myReader.close();
            return carti;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
