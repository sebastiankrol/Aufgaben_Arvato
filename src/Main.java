import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        String dir = System.getProperty("user.dir")+ "\\data"; //Ordnerpfad für die Textdateien
        String pathList1 = dir + "\\List1.txt"; // Pfad für Liste1
        String pathList2 = dir + "\\List2.txt"; //Pfad für Liste2


       List<String> onlyInList1 = fileStreamUsingFiles(pathList1); // Wörter durch die Funktion in Array schreiben
       List<String> onlyInList2 = fileStreamUsingFiles(pathList2);
       List<String> onlyInBoth = new ArrayList<>();

       //Schleife durch beide Listen, wenn sich die Wörter in beiden Listen befindet werden die Wörter in onlyInBoth geschriebe
      for(int i =0;i<onlyInList1.size();i++){
          for(int j = 0;j <onlyInList2.size();j++){
              if(onlyInList1.get(i).equals(onlyInList2.get(j))) {
                  onlyInBoth.add(onlyInList1.get(i));
              }
          }
      }
      //Da wir die gleichen Wörter vorher herausgefunden haben können jetzt diese einfach aus den beiden Listen gelöscht werden
        // Dadurch erhalten wir die gewünschten Werte
      onlyInList1.removeAll(onlyInBoth);
      onlyInList2.removeAll(onlyInBoth);

        //Json Objekt wird erstellt.
        JsonObject words = new JsonObject();
        words.addProperty("onlyInFirst", String.valueOf(onlyInList1));
        words.addProperty("onlyInSecond", String.valueOf(onlyInList2));
        words.addProperty("onlyInBoth", String.valueOf(onlyInBoth));
        System.out.println(words.toString());





    }
    //Methode zum auslesen von Textdateien, Ergebnis gibt eine Liste mit den Wörtern aus
    private static List<String> fileStreamUsingFiles(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            lines.forEach(e->list.add(e));
            lines.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
        return list;
    }
}


