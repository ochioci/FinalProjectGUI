import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Locale;

public class Game {
    Dictionary myDict = new Dictionary("C://Users/BT_1E10_20/IdeaProjects/Beckett Randlett/src/words.txt");
    private int score = 0;
    private ArrayList<String> wordsPlayed = new ArrayList<String>();
    private double timeLimit = 90.0;
    private double startTime = -1.0;
    private Board gameBoard;
    public Game () throws IOException {
        gameBoard = new Board(4,4);
    }
    public void playNewBoard() throws IOException {
        gameBoard.smartPopulate();
        score = 0;
        wordsPlayed = new ArrayList<String>();
        startTime = Instant.now().getEpochSecond();
    }

    public void displayBoard() {
        gameBoard.printBoard();
    }

    public int playWord(String word) throws IOException { //REMEMBER TO INCLUDE CLAUSE FOR IF THE WORD HAS ALREADY BEEN PLAYED
        if (Instant.now().getEpochSecond() > startTime + timeLimit) {
            return -1; //out of time
        }
        if (!gameBoard.validateWord(word)) {
            return -2; // not found on board
        }
        if (myDict.checkWord(word)) {
            score += getPointValue(word);
            wordsPlayed.add(word.toLowerCase());
            return 1; //success
        } else {
            return 0; //invalid word - not in dictionary;
        }

    }

    public void displayTimeRemaining() {
        System.out.println("You have " + (startTime + timeLimit - Instant.now().getEpochSecond()) + " time remaining.");
    }

    public void displayScore () {
        System.out.println("You scored " + score + " points!");
    }

    public void playLastBoard() {
        wordsPlayed = new ArrayList<String>();
        score = 0;
        startTime = Instant.now().getEpochSecond();
    }

    public int getPointValue(String word) {
        return (int) (100 * Math.pow(2, word.length()-2));
    }

}
