public class Board {
    private int width;
    private int height;
    private String[][] tiles;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new String[width][height];
        populate();
    }

    private void populate() {
        for (int i = 0; i < width; i++) {
            for (int n = 0; n < height; n++) {
                tiles[i][n] = randomLetter();
            }
        }
    }

    private String randomLetter() {
        int index = (int) (Math.random() * 26);
        return alphabet.substring(index, index+1);
    }

    public void printBoard() {
        for (int i = 0; i < width; i++) {
            for (int n = 0; n < height; n++) {
                System.out.print(tiles[i][n] + "  ");
            }
            System.out.println();
        }
    }

    public void validateWord()


}
