package tetrisGame;

/**
 * Tämä luokka hoitaa pelilaudan toteutuksen. Pelilaudall on kaikki jo tippuneet
 * tetromiinot
 *
 * @author Josse
 *
 */
public class Board {

    private final int[][] board;
    private final boolean[] isRowFilled;

    /**
     * Construktori luo uuden tyhjän pelilaudan
     */
    public Board() {
        this.board = new int[20][10];
        setAllToZero();
        this.isRowFilled = new boolean[20];
        setIsRowFilledFalse();
    }

    /**
     * alustaa kaikki isRowFilled taulukon osat falseks;
     */
    public void setIsRowFilledFalse() {
        for (int i = 0; i < 20; i++) {
            this.isRowFilled[i] = false;
        }
    }

    /**
     * nolla koko talukon, niin että siinä on vain Empty tetromiinoja eli 0
     */
    public void setAllToZero() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    /**
     * palauttaa boardin tilanteen
     *
     * @return board
     */
    public int[][] getBoardStatus() {
        return this.board;
    }

    /**
     *
     * @return isRowFilled taulukko, joka kertoo mitkä rivit ovat täynnä
     */
    public boolean[] getIsRowFilledStatus() {
        return this.isRowFilled;
    }

    /**
     * asettaa boardin ruutuun halutun arvon
     *
     * @param x boardin x kordinaatti
     * @param number luku, joka vastaa jotakin tetrominon muotoa
     * @param y boardin y kordinaatti
     */
    public void setNumberToBoard(int x, int y, int number) {
        if (y > 19 || x > 9 || y < 0 || x < 0) {
            return;  /*jos y ja x ovat boardin ulkopuolel ei tapahdu mitään*/

        }
        if (number < 0 || number > 7) {
            return; /*jos numero ei ole 0-7 (shape eunumin sisällä) ei tapahdu 
             * mitää*/

        }
        this.board[y][x] = number;
    }

    /**
     * asettaa annetun tetrominon boardiin niin, että tetrominon origo (pala
     * 0,0)
     * on kohdassa globalX,globalY
     *
     * @param globalX tetrominon origon x kordinaatti pelilaudalla
     * @param tetromino tetromiino, joka asetetaan pelilaudalle
     * @param globalY tetrominon origon y kordinaatti pelilaudalla
     */
    public void setTetrominoToBoard(int globalX, int globalY, Tetromino tetromino) {
        int shape = tetromino.getShape().ordinal();
        for (int i = 0; i < 4; i++) {
            int x = tetromino.getX(i);
            int y = tetromino.getY(i);
            int helpX = globalX + x;
            int helpY = globalY + y;

            setNumberToBoard(helpX, helpY, shape);
        }
    }

    /**
     * Tarkistaa, mitkä pelilaudan rivit ovat täynnä
     *
     * @return täytettyjen rivien määrä
     */
    public int checkWhatLinesAreFull() {
        int fullLines = 0;
        boolean isFull = true;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.board[i][j] == 0) {
                    isFull = false;
                }
            }
            if (isFull) {
                this.isRowFilled[i] = true;
                fullLines++;
            }
            isFull = true;
        }
        return fullLines;
    }

    /**
     * poistaa täysinäiset rivit jotka on määritelty isRowFilled talukossa
     *
     */
    public void removeFullLines() {
        for (int i = 0; i < 20; i++) {
            if (this.isRowFilled[i] == true) {
                dropDownToLine(i);
                this.isRowFilled[i] = false;
            }
        }

    }

    /**
     * tiputtaa alas rivit annetun rivin yläpuolelta (täten poistaa rivin)
     *
     * @param line poistettava rivi
     */
    public void dropDownToLine(int line) {
        if (line > 19 || line < 0) {
            return;
        }
        for (int i = line; i > 1; i--) {
            System.arraycopy(this.board[(i - 1)], 0, this.board[i], 0, 10);
        }
        for (int i = 0; i < 9; i++) {
            this.board[0][i] = 0;
        }
    }
}
