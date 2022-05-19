import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Runner {
    private static Game myGame;

    static {
        try {
            myGame = new Game();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Runner() throws FileNotFoundException {
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
        String choice = choiceScanner.nextLine();
        Scanner wordInputScanner = new Scanner(System.in);
        if (choice.equals("n")) {
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
                } else if (result == 0) {
                    System.out.println("Sorry! Invalid word");
                    myGame.displayTimeRemaining();
                } else if (result == 1) {
                    System.out.println("Nice!");
                    myGame.displayTimeRemaining();
                } else if (result == -2) {
                    System.out.println("Sorry! That word was not found on the board");
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
                } else if (result == 0) {
                    System.out.println("Sorry! Invalid word");
                    myGame.displayTimeRemaining();
                } else if (result == 1) {
                    System.out.println("Nice!");
                    myGame.displayTimeRemaining();
                } else if (result == -2) {
                    System.out.println("Sorry! That word was not found on the board");
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
