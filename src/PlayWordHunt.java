import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class PlayWordHunt {
    private static Game myGame;
    public static String playerName = "anonymous";
    static {
        try {
            myGame = new Game();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PlayWordHunt() throws FileNotFoundException {
    }

    public static void main (String[] args) throws IOException {
        menu();


//        Board testBoard = new Board(4,4);
//        testBoard.smartPopulate();
    }



    public static void dash() {
        System.out.println(" ------------------------ ");
    }

    public static void menu() throws IOException {
        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("Would you like to...");
        System.out.println("Start a [n]ew game?");
        System.out.println("Replay the [s]ame board?");
        System.out.println("[q]uit?");
        System.out.println("[v]iew the leaderboard");
        System.out.println("[s]et your username to be shown in the leaderboard");
        String choice = choiceScanner.nextLine();
        Scanner wordInputScanner = new Scanner(System.in);
        if (choice.equals("s")) {

            System.out.println("Your current username is: " + playerName + "\n Please input your new username...");
            playerName = wordInputScanner.nextLine();
            System.out.println("Your username is now: " + playerName + " !");
            choice = "";
            menu();

        }
        else if (choice.equals("v")) {
            Leaderboard.displayHighScores();
            menu();

        }
        else if (choice.equals("n")) {
            myGame.playNewBoard();
            boolean playing = true;
            while (playing) {
                myGame.displayBoard();
                System.out.println("Input a word > ");
                String inWord = wordInputScanner.nextLine();
                int result = myGame.playWord(inWord);
                if (result == -1) {
                    dash();
                    System.out.println("Sorry! Time's up");
                    playing = false;
                    myGame.displayEndgameStats();
                } else if (result == 0) {
                    System.out.println("Sorry! Invalid word");
                    myGame.displayTimeRemaining();
                } else if (result == 1) {
                    System.out.println("Nice!");
                    myGame.displayTimeRemaining();
                } else if (result == -2) {
                    System.out.println("Sorry! That word was not found on the board");
                    myGame.displayTimeRemaining();
                } else if (result == -3) {
                    System.out.println("Hey! you already played that word");
                    myGame.displayTimeRemaining();
                }
            }
            myGame.displayScore();
            dash();
            menu();
        } else if (choice.equals("s")) {
            myGame.playLastBoard();
            boolean playing = true;
            while (playing) {
                myGame.displayBoard();
                System.out.println("Input a word > ");
                String inWord = wordInputScanner.nextLine();
                int result = myGame.playWord(inWord);
                if (result == -1) {
                    dash();
                    System.out.println("Sorry! Time's up");
                    playing = false;
                    myGame.displayEndgameStats();
                } else if (result == 0) {
                    System.out.println("Sorry! Invalid word");
                    myGame.displayTimeRemaining();
                } else if (result == 1) {
                    System.out.println("Nice!");
                    myGame.displayTimeRemaining();
                } else if (result == -2) {
                    System.out.println("Sorry! That word was not found on the board");
                    myGame.displayTimeRemaining();
                } else if (result == -3) {
                    System.out.println("Hey! you already played that word");
                    myGame.displayTimeRemaining();
                }
            }
            myGame.displayScore();
            dash();
            menu();
        } else if (choice.equals("q")) {
            return;
        }
        else {
            System.out.println("Invalid Choice!");
            menu();
        }
    }



}
