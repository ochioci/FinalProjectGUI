import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class PlayWordHuntGUI {
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

    public static void main (String[] args) throws IOException {
        menu();
    }



    public static void wordHuntGUI()  throws IOException {
//        menu();
        JFrame frame = new JFrame("Word Hunt");
        frame.toFront();
        frame.repaint();
        frame.setLayout(new GridLayout(8, 4, 10, 10));
        frame.setSize(400,800);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        myGame = new Game();

        myGame.playNewBoard();
//        myGame.displayBoard();
        String[][] board = myGame.getBoard();
//        JPanel boardPanel = new JPanel();
//        int boardPanelWidth = 400;
//        int boardPanelHeight = 400;

//        boardPanel.setSize(boardPanelWidth, boardPanelHeight);

        Font tileFont = new Font("SansSerif", Font.BOLD, 30);
        for (int i = 0; i < board.length; i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1,4, 10, 10));
            for (int n = 0; n < board[0].length; n++) {
                JLabel tileLabel = new JLabel(board[i][n], SwingConstants.CENTER);
                Border tileBorder = BorderFactory.createLineBorder(Color.black);
                tileLabel.setBorder(tileBorder);
                tileLabel.setOpaque(true);
                tileLabel.setBackground(Color.ORANGE);
                tileLabel.setFont(tileFont);

                rowPanel.add(tileLabel);

            }
            frame.add(rowPanel);
        }

        Font infoFont = new Font("SansSerif", Font.PLAIN, 20);

        JLabel timeRemaining = new JLabel("", SwingConstants.CENTER);
        timeRemaining.setFont(infoFont);

        JLabel wordStatus = new JLabel(" -- ", SwingConstants.CENTER);
        wordStatus.setFont(infoFont);

        JTextField entryField = new JTextField(16); //accepts max 10 characters
        entryField.setFont(infoFont);

        JLabel scoreLabel = new JLabel("Find a word!", SwingConstants.CENTER);
        scoreLabel.setFont(infoFont);

        JPanel inputBoxPanel = new JPanel();
        inputBoxPanel.setLayout(new GridLayout());

        inputBoxPanel.add(scoreLabel);
        inputBoxPanel.add(entryField);



        JPanel scorePanel = new JPanel();

        scorePanel.setFont(infoFont);
        scorePanel.add(scoreLabel);

        JPanel timePanel = new JPanel();
        timePanel.setFont(infoFont);
        timePanel.add(timeRemaining);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());
        statusPanel.setFont(infoFont);
        statusPanel.add(wordStatus);


        frame.add(inputBoxPanel);
        frame.add(scorePanel);
        frame.add(statusPanel);
        frame.add(timePanel);
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (myGame.getTimeLeft() <= 0.0) {
                    timeRemaining.setVisible(false);
                }
                timeRemaining.setText(String.valueOf(myGame.getTimeLeft()) + " seconds remaining");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int result = myGame.playWord(entryField.getText());
                        scoreLabel.setText(String.valueOf("Score: " + myGame.getScore()));
                        if (result == -1) {
                            wordStatus.setText("Game Over!");
                            timeRemaining.setVisible(false);
                            entryField.setVisible(false);
//                            menu();
                        }
                        if (result == -3) {
                            wordStatus.setText("You already played " + "'" + entryField.getText() + "'");
                        }
                        if (result == -2) {
                            wordStatus.setText("'" + entryField.getText() + "'" + " was not found on the board");
                        }
                        if (result == 0) {
                            wordStatus.setText("'" + entryField.getText() + "'" + " is not a word");
                        }
                        if (result == 1) {
                            wordStatus.setText("Nice!");
                        }
//                        wordStatus.setText(String.valueOf(result));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    entryField.setText("");
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("hiii");

            }
        };
        entryField.addKeyListener(kl);
//        frame.add(entryField);

//        frame.add(rowPanel);
        frame.setVisible(true);
    }

    public static int getColorInt () {
        return (int)(Math.random() * 255);
    }


    public static void dash() {
        System.out.println(" ------------------------ ");
    }

    public static void menu() throws IOException {
        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("Would you like to...");
        System.out.println("Start a [n]ew game?");
//        System.out.println("Replay the [s]ame board?");
        System.out.println("[q]uit?");
        System.out.println("[v]iew the leaderboard");
        System.out.println("set your [u]sername to be shown in the leaderboard");
        String choice = choiceScanner.nextLine();
        Scanner wordInputScanner = new Scanner(System.in);
        if (choice.equals("u")) {

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
            wordHuntGUI();
            menu();
        }
//        else if (choice.equals("s")) {
//            myGame.playLastBoard();
//            boolean playing = true;
//            while (playing) {
//                myGame.displayBoard();
//                System.out.println("Input a word > ");
//                String inWord = wordInputScanner.nextLine();
//                int result = myGame.playWord(inWord);
//                if (result == -1) {
//                    dash();
//                    System.out.println("Sorry! Time's up");
//                    playing = false;
//                    myGame.displayEndgameStats();
//                } else if (result == 0) {
//                    System.out.println("Sorry! Invalid word");
//                    myGame.displayTimeRemaining();
//                } else if (result == 1) {
//                    System.out.println("Nice!");
//                    myGame.displayTimeRemaining();
//                } else if (result == -2) {
//                    System.out.println("Sorry! That word was not found on the board");
//                    myGame.displayTimeRemaining();
//                } else if (result == -3) {
//                    System.out.println("Hey! you already played that word");
//                    myGame.displayTimeRemaining();
//                }
//            }
//            myGame.displayScore();
//            dash();
//            menu();
//        }
        else if (choice.equals("q")) {
            System.exit(0);
            return;

        }
        else {
            System.out.println("Invalid Choice!");
            menu();
        }
    }



}
