import java.io.*;
import java.util.ArrayList;

public class Leaderboard {

    public static void displayHighScores() throws IOException {
        File db = new File("src/leaderboard.txt");
        BufferedReader br = new BufferedReader(new FileReader(db));
        String st;
        int count = 0;
        while ((st = br.readLine()) != null && count < 10) {
            count++;
            System.out.println("#" + count + ": " + st);

        }
    }

    public static void addScore(int score) throws IOException {
        File db = new File("src/leaderboard.txt");
        BufferedReader br = new BufferedReader(new FileReader(db));
        String st;
        ArrayList<String> entries = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            if (st.indexOf("-") > -1) {
                entries.add(st);
            }

        }

        entries.add(PlayWordHuntGUI.playerName + " - " + score);

//        for (String e : entries) {
//            System.out.println(Integer.parseInt(e.substring(e.indexOf("-")+2)));
//        }

        entries.sort((s1, s2) -> (Integer.parseInt(s1.substring(s1.indexOf("-")+2)) - Integer.parseInt(s2.substring(s2.indexOf("-")+2)) ));

        //something is causing values in leaderboard.txt and/or entries to be replaced by adjacent values. the cause is unknown
        //the cause is most likely the sorting algorithm below
        //ok i think i fixed this by caving and using the lambda expression

//        for (int i = 0; i < entries.size(); i++) {
//            String eI = entries.get(i);
//            int iEntryVal = Integer.parseInt(eI.substring(eI.indexOf("-")+2));
//
//
//            for (int n = i + 1; n < entries.size(); n++) {
//                String eN = entries.get(n);
//                int nEntryVal = Integer.parseInt(eN.substring(eN.indexOf("-")+2));
//
//                if (nEntryVal < iEntryVal) {
//                    String temp = eI;
//                    entries.set(i, eN);
//                    entries.set(n, temp);
//                }
//
//            }
//        }
//        System.out.println(entries);
        try {
            FileWriter myWriter = new FileWriter("src/leaderboard.txt");
            String toWrite = "";
            for (int i = 0; i < entries.size(); i++) {
                toWrite = entries.get(i) + "\n" + toWrite;
            }
            myWriter.write(toWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }

}
