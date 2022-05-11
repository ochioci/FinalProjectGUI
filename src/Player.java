import java.util.ArrayList;
public class Player {
    private int name;
    private int score;
    private int timeLeft;
    private int wins;
    private ArrayList<String> words = new ArrayList<String>();

    public Player(int name, int score) {
        this.name = name;
        this.score = 0;
        this.timeLeft = -1;
        this.wins = 0;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
    public int getScore() {
        return score;
    }

    public int getName() {
        return name;
    }

    public int addWord(String word) {
        //validate word, handle with dictionary
        //if word is valid, append to words
        //increment score accordingly
        //return the point value of word
        return 0;
    }


    public static Player winner(Player a, Player b) {
        if (a.getScore() > b.getScore()) {
            return a;
        } else if (b.getScore() > a.getScore()) {
            return b;
        }
        return null;
    }
}
