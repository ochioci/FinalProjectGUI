import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
public class Game {
    Dictionary myDict = new Dictionary("C://Users/BT_1E10_20/IdeaProjects/Beckett Randlett/src/words.txt");
    private int score = 0;
    private double timeLimit = 15.0;
    private double startTime = -1.0;
    private Board gameBoard;
    public Game () throws IOException {
        gameBoard = new Board(4,4);
    }
    public void playNewBoard() throws IOException {
        gameBoard.smartPopulate();
        score = 0;
        startTime = Instant.now().getEpochSecond();
    }

    public void displayBoard() {
        gameBoard.printBoard();
    }

    public int playWord(String word) throws IOException {
        if (Instant.now().getEpochSecond() > startTime + timeLimit) {
            return -1; //out of time
        }
        if (!gameBoard.validateWord(word)) {
            return -2; // not found on board
        }
        if (myDict.checkWord(word)) {
            score += getPointValue(word);
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
        score = 0;
        startTime = Instant.now().getEpochSecond();
    }

    public int getPointValue(String word) {
        return (int) (100 * Math.pow(2, word.length()-2));
    }

}
