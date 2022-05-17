import java.util.ArrayList;

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

    public void populate() {
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

    public boolean validateWord(String word) {
        //takes a word, sees if it can be found within the board
        ArrayList<ArrayList<Integer>> roots = new ArrayList<ArrayList<Integer>>(); //all possible beginnings of word

        //populate roots
        for (int i = 0; i < tiles.length; i++) {
            for (int n = 0; n < tiles[0].length; n++) {
                if (tiles[i][n].toLowerCase().equals(word.toLowerCase().substring(0,1))) {
                    roots.add(new ArrayList<Integer>());
                    roots.get(roots.size()-1).add(i);
                    roots.get(roots.size()-1).add(n);
                }
            }
        }

        for (int i = 0; i < roots.size(); i++) {

            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            temp.add(new ArrayList<Integer>());
            temp.get(0).add(roots.get(i).get(0));
            temp.get(0).add(roots.get(i).get(1));

            if (recursiveValidationHelper(word.substring(0,1), word, temp)) {return true;}
        }

        return false;
    }

    //recursively attempts to find a given word, returns true if a solution can be found
    public boolean recursiveValidationHelper(String progress, String target, ArrayList<ArrayList<Integer>> usedTiles) {
        if (progress.equals(target)) {return true;}

        //the current tile we are operating on
        int currentRow = usedTiles.get(usedTiles.size()-1).get(0);
        int currentCol = usedTiles.get(usedTiles.size()-1).get(1);

        //the letter being searched for in this round of the program
        String nextLetter = target.substring(progress.length(), progress.length()+1);

        ArrayList<ArrayList<Integer>> nextTilesRaw = getAdjacentTiles(currentRow, currentCol);
        ArrayList<ArrayList<Integer>> nextTiles = new ArrayList<ArrayList<Integer>>();
        //add all tiles from nextTilesRaw not found in usedTiled to nextTiles
//        System.out.println("next tiles raw: " + nextTilesRaw);
        for (int i = 0; i < nextTilesRaw.size(); i++) {
            boolean passing = true;

            for (int n = 0; n < usedTiles.size(); n++) {
                if (usedTiles.get(n).get(0) == nextTilesRaw.get(i).get(0) && usedTiles.get(n).get(1) == nextTilesRaw.get(i).get(1)) {

                    passing = false;
                }

            }
            //tiles[nextTilesRaw.get(i).get(0)][nextTilesRaw.get(i).get(1)].toUpperCase().equals(nextLetter.toUpperCase())
            if (passing) {nextTiles.add(nextTilesRaw.get(i));}
        }

//        System.out.println();
//        System.out.println("current location: " + currentRow + ", " + currentCol);
//        System.out.println("current progress: " + progress);
//        System.out.println("adjacent tiles: " + nextTilesRaw);
//        System.out.println("possible next tiles: " + nextTiles);
//        System.out.println();

        //the issue lies in determining all of the next possible tiles! for some reason certain tiles are filtered out when they should not be, causing false negatives
        //okay apparently the tiles to the left and right in the same row are filtered out for some reason. everything else works as intended

        ArrayList<Boolean> results = new ArrayList<Boolean>();
        for (int i = 0; i < nextTiles.size(); i++) {
            int rowCoord = nextTiles.get(i).get(0);
            int colCoord = nextTiles.get(i).get(1);

            if (tiles[rowCoord][colCoord].equals(nextLetter)) {


                ArrayList<ArrayList<Integer>> usedTilesCopy = new ArrayList<ArrayList<Integer>>();
                for (int n = 0; n < usedTiles.size(); n++) {
                    usedTilesCopy.add(usedTiles.get(n));
                }
                usedTilesCopy.add(nextTiles.get(i));


                results.add(recursiveValidationHelper(progress + nextLetter, target, usedTilesCopy));
            }
        }
//        System.out.println();
//        System.out.println("target: " + target);
//        System.out.println("progress: " + progress);
//        System.out.println("next tiles: " + nextTiles);
//        System.out.println();

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i)) {
                return true;
            }
        }
        return false;
        //if no results are true, return false!



    }


    public ArrayList<ArrayList<Integer>> getAdjacentTiles(int row, int col){
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> nw = new ArrayList<Integer>(); //northwest (adjacent tile in the upper left corner)
        ArrayList<Integer> nc = new ArrayList<Integer>(); //north cardinal (right above)
        ArrayList<Integer> ne = new ArrayList<Integer>(); //...etc
        ArrayList<Integer> ec = new ArrayList<Integer>();
        ArrayList<Integer> se = new ArrayList<Integer>();
        ArrayList<Integer> sc = new ArrayList<Integer>();
        ArrayList<Integer> sw = new ArrayList<Integer>();
        ArrayList<Integer> wc = new ArrayList<Integer>();

        int rowEnd = tiles.length-1;
        int colEnd = tiles[0].length-1;


        //wc
        if (col > 0) {
            wc.add(row);
            wc.add(col-1);
            output.add(wc);
        }


        //sw
        if (col > 0 && row < rowEnd) {
            sw.add(row + 1);
            sw.add(col - 1);
            output.add(sw);
        }


        //ec
        if (col < colEnd) {
            ec.add(row);
            ec.add(col+1);
            output.add(ec);
        }

        //se
        if (row < rowEnd && col < colEnd) {
            se.add(row+1);
            se.add(col+1);
            output.add(se);
        }

        //sc
        if (row < rowEnd) {
            sc.add(row+1);
            sc.add(col);
            output.add(sc);
        }



        //nw
        if (row > 0 && col > 0) {
            nw.add(row-1);
            nw.add(col-1);
            output.add(nw);
        }

        //nc
        if (row > 0) {
            nc.add(row-1);
            nc.add(col);
            output.add(nc);
        }


        //ne
        if (row > 0 && col < colEnd) {
          ne.add(row - 1);
          ne.add(col + 1);
          output.add(ne);
        }

        return output;
    }



}
