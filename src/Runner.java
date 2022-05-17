import java.util.Scanner;
public class Runner {
    private static Game myGame;
    public static void main (String[] args) {
        Game myGame = new Game();
        Scanner s = new Scanner(System.in);
        menu();
    }

    public static void menu() {
        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("Would you like to...");
        System.out.println("Start a [n]ew game?");
        System.out.println("Replay the [s]ame board?");
        System.out.println("[q]uit?");
        String choice = choiceScanner.nextLine();
        if (choice.equals("n")) {
            myGame.playNewBoard();

        } else if (choice.equals("s")) {
            myGame.playLastBoard();
        } else if (choice.equals("q")) {
            return;
        }
        else {
            System.out.println("Invalid Choice!");
            menu();
        }
    }



}
