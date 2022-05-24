import java.io.*;
import java.util.ArrayList;

public class Leaderboard {

    public static void displayHighScores() throws IOException {
        File db = new File("C://Users/BT_1E10_20/IdeaProjects/Beckett Randlett/src/leaderboard.txt");
        BufferedReader br = new BufferedReader(new FileReader(db));
        String st;
        while ((st = br.readLine()) != null) {
            System.out.println(st);
        }
    }

    public static void addScore(int score) throws IOException {
        File db = new File("C://Users/BT_1E10_20/IdeaProjects/Beckett Randlett/src/leaderboard.txt");
        BufferedReader br = new BufferedReader(new FileReader(db));
        String st;
        ArrayList<String> entries = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            entries.add(st);
        }

        entries.add(Runner.playerName + " - " + score);

        for (int i = 0; i < entries.size(); i++) {
            try {
                int iVal = Integer.parseInt(entries.get(i).substring(entries.get(i).indexOf("-") + 2));

                for (int n = i + 1; n < entries.size(); n++) {
                    int nVal = Integer.parseInt(entries.get(n).substring(entries.get(n).indexOf("-") + 2));

                    if (nVal > iVal) {
                        String temp = entries.get(i);
                        entries.set(i, entries.get(n));
                        entries.set(i, temp);
                    }
                }
            }
            catch (Error e) {}
        }
        System.out.println(entries);
        try {
            FileWriter myWriter = new FileWriter("C://Users/BT_1E10_20/IdeaProjects/Beckett Randlett/src/leaderboard.txt");
            String toWrite = "";
            for (int i = 0; i < entries.size(); i++) {
                entries.set(i, entries.get(i) + "\n");
                toWrite = entries.get(i) + toWrite;
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
