import java.io.*;
import java.util.ArrayList;

public class Dictionary {

    private static File db;
    private static BufferedReader br;
    public Dictionary (String dbName) throws FileNotFoundException {
    }

    public static ArrayList<String> getListOfWords () throws IOException {
        db = new File("src/words.txt");
        br = new BufferedReader(new FileReader(db));
        String st;
        ArrayList<String> output = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            output.add(st);
        }
        return output;
    }

    public static boolean checkWord(String query) throws IOException {
        db = new File("src/words.txt");
        br = new BufferedReader(new FileReader(db));
        String st;
        while ((st = br.readLine()) != null) {
            if(st.toLowerCase().equals(query.toLowerCase())) {return true;}
        }

        return false;
    }



}
