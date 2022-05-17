import java.time.Instant;
public class Game {
    private int score = 0;
    private double timeLimit = 100.0;
    private double startTime = -1.0;
    private Board gameBoard;
    public Game () {
        gameBoard = new Board(4,4);
    }
    public void playNewBoard() {
        gameBoard.populate();
        score = 0;
        startTime = Instant.now().getEpochSecond();
    }
    public int playWord(String word) {
        if (Instant.now().getEpochSecond() > startTime + timeLimit) {
            return -1; //out of time
        }
        if (Dictionary.checkWord(word)) {
            score += getPointValue(word);
            return 1; //success
        } else {
            return 0; //invalid word;
        }

    }

    public void playLastBoard() {
        score = 0;
        startTime = Instant.now().getEpochSecond();
    }

    public int getPointValue(String word) {
        return (int) (100 * Math.pow(2, word.length()-2));
    }

}
