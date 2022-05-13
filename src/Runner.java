import java.util.Scanner;
public class Runner {
    public static void main (String[] args) {
        Board gameBoard = new Board(4,4);
        gameBoard.printBoard();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Word to validate");
            String word = s.nextLine();
            System.out.println(gameBoard.validateWord(word.toUpperCase()));
        }
    }
}
