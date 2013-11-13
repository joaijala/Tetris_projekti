/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import userInterface.GameScreen;

/**
 *
 * @author Järjestelmänvalvoja Tässä luokassa tapahtuu itse pelilooppi ja koko
 * pelin pyörittäminen
 */
public class GameLogic {

    private static final double NANOSEC_TO_MILLISEC = 0.000001;
    private int globalX;
    private int globalY;
    private Tetromino fallingTetromino;
    private Board board;
    private boolean isPaused = false;
    private boolean isTetrominoFalling = false;
    private boolean isGameStarted = false;
    /**
     * Pitää kirjaa onko pelaaja liikuttanut palikkaa. Se on positiivinen jos
     * liikutettu oikealle, negatiivinen jos vasemmalle ja 0 ei ole liikutettu
     */
    private int isMoved;
    /**
     * Pitää kirjaa onko pelaaja pyörittänyt palikkaa. Se on poitiivinen jos on
     * pyöritetty oikealle ja neggatiivinen jos on pyöritetty oikealle
     */
    private int isRotated;
    /**
     * softDrop antaa peliaajan tiputtaa tetrominon yhden alaspäin
     */
    private int softDrop;
    private int cleardRows;
    private double timeOfLastDrop;
    private double dropIntervall;
    private GameScreen gameScreen;
    private ControllListener contollistener;

    public GameLogic(GameScreen gameScreen) {
        this.board = new Board();
        this.fallingTetromino = new Tetromino();
        this.isMoved = 0;
        this.isRotated = 0;
        this.cleardRows = 0;
        this.softDrop = 0;
        this.dropIntervall = 50;
        this.gameScreen = gameScreen;

    }

    /**
     * gameloop pyöeittää itse pelin pelilooppia
     */
    public void GameLoop() {

        this.isGameStarted = true;
        setNewFallingTetromino();
        /*Pelilooppi*/
        while (this.isGameStarted) {

            /*jos peli on pausella pelilooppi odottaa että peli ei ole enää pausella*/
            if (this.isPaused) {
                waitUntillNotPaused();
            }
            /**
             * jos on kulunut tarpeeksi aikaa edellisestä tetrominon
             * tiputtamisesta tetromino tippuu alaspäin
             */
            if (this.isTetrominoFalling && getCurrentTimeInMilliseconds() > (this.dropIntervall + this.timeOfLastDrop)) {
                dropOneLineDown();
            }
            /**
             * jos pelaaja on liikuttanut tetrominoa peli siitää sitä jos
             * mahdollista
             */
            if (this.isMoved != 0) {
                moveTetromino();
            }
            /**
             * jos pelaaja on pyörittänyt kappaletta,peli pyörittää sitä tässä
             * jos mahdollista
             */
            if (this.isRotated != 0) {
                rotateTetromino();
            }
            /**
             * jos pelaaja on painanut softDroppia tetromino tippuu yhden
             * askeleen alaspäin;
             */
            if (softDrop > 0) {
                dropOneLineDown();
                softDrop--;
            }

        }

    }

    /**
     * asettaa tetromiinolle uuden muodon ja laittaa sen pelikentän huipulle,
     * sekä nollaa liikuttamiseen liittyvät muututjat
     */
    private void setNewFallingTetromino() {
        this.fallingTetromino.setRandomShape();
        this.globalX = 4;
        this.globalY = 1;
        this.isMoved = 0;
        this.isRotated = 0;
        this.softDrop = 0;
        this.isTetrominoFalling = true;
        this.timeOfLastDrop = getCurrentTimeInMilliseconds();
        gameScreen.repaint();
    }

    /**
     * dropOneLineDown pudottaa tetrominon alaspäin jos se on mahdollista,
     * muuten asettaa uuden tetrominon
     */
    private void dropOneLineDown() {
        if (isMovePossible(this.fallingTetromino, this.globalX, this.globalY + 1)) {
            this.globalY++;
            this.timeOfLastDrop = getCurrentTimeInMilliseconds();
            gameScreen.repaint();
        }
        else {
            this.isTetrominoFalling = false;
            this.board.setTetrominoToBoard(globalX, globalY, this.fallingTetromino);
            takeCareOfFullLines();
            setNewFallingTetromino();
            gameScreen.repaint();
        }
    }

    /**
     * Huolehtii täysinäisten rivien tarkistamisesta ja niiden poistamisesta
     * Tulevaisuudessa myös kertoo miten paljon pisteitä annetaan
     */
    private void takeCareOfFullLines() {
        int fullLines = this.board.checkWhatLinesAreFull();
        if (fullLines != 0) {
            this.board.removeFullLines();
        }
    }

    /**
     * käsittelee tetrominon pyörittämistä
     */
    public void rotateTetromino() {

        if (this.isRotated > 0) {
            this.fallingTetromino.rotateRight();
            if (!isMovePossible(fallingTetromino, globalX, globalY)) {
                this.fallingTetromino.rotateLeft();
            }
            this.gameScreen.repaint();
            this.isRotated--;
        }
        else if (this.isRotated < 0) {
            this.fallingTetromino.rotateLeft();
            if (!isMovePossible(fallingTetromino, globalX, globalY)) {
                this.fallingTetromino.rotateRight();
            }
            this.gameScreen.repaint();
            this.isRotated++;
        }
    }

    /**
     * MoveTetromino hoitaa tetrominon liikuttamisen
     */
    public void moveTetromino() {
        if (this.isMoved > 0) {
            if (isMovePossible(this.fallingTetromino, this.globalX + 1, this.globalY)) {
                this.globalX++;
                this.isMoved--;
                gameScreen.repaint();
            }
        }
        if (this.isMoved < 0) {
            if (isMovePossible(this.fallingTetromino, this.globalX - 1, this.globalY)) {
                this.globalX--;
                this.isMoved++;
                gameScreen.repaint();
            }
        }
    }

    /**
     * isMovePossible tarkistaa, onko aijottu siirto mahdollinen, jos se ei ole
     * mallinen palautuu false muuten true
     */
    private boolean isMovePossible(Tetromino tetromino, int origoX, int origoY) {
        int[][] boardStatus = board.getBoardStatus();
        for (int i = 0; i < 4; i++) {
            if ((tetromino.getY(i) + origoY) > 19 || (tetromino.getX(i) + origoX) > 9 || (tetromino.getX(i) + origoX) < 0) {
                return false;
            }
            if (boardStatus[(tetromino.getY(i) + origoY)][(tetromino.getX(i) + origoX)] != 0) {
                return false;
            }

        }
        return true;
    }

    /**
     * ei tee mitään niin kauan kuin peli on tauolla. Odottamisen tarkoitus on,
     * että näppäinkuuntelija pääsee väliin reagoimaan
     */
    public void waitUntillNotPaused() {
        double time = getCurrentTimeInMilliseconds();
        while (isPaused) {
            while (getCurrentTimeInMilliseconds() < time + 10) {

            }
            time = getCurrentTimeInMilliseconds();
        }
    }

    /**
     *
     * Tulostaa tippuvan tetrominon kordinaatit pelikentässä, *
     * private void printFallingTetromino(){
     * for(int i=0;i<4;i++){
     * System.out.println((this.fallingTetromino.getX(i)+globalX)+",
     * "+(this.fallingTetromino.getY(i)+globalY));
     * }
     * System.out.println("");
     * }
     * private void printBoard(){
     * int [][] boardStatus=board.getBoardStatus();
     * for (int i=0;i<20;i++){
     * for(int j=0;j<10;j++){
     * System.out.print(boardStatus[i][j]+", ");
     * }
     * System.out.println("");
     * }
     * }
     */
    /**
     *
     * @return palauttaa juuri tippuvan tetrominon
     */
    public Tetromino getTetromino() {
        return this.fallingTetromino;
    }

    /**
     *
     * @return palauttaa pelin Boardin
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     *
     * @return globalX
     */
    public int getGlobalX() {
        return this.globalX;
    }

    /**
     *
     * @return globalY
     */
    public int getGlobalY() {
        return this.globalY;
    }

    /**
     *
     * @return palauttaa javan virtuaalikoneen tämänhetkisen ajan
     * millisekuntteina
     */
    private double getCurrentTimeInMilliseconds() {
        return System.nanoTime() * NANOSEC_TO_MILLISEC;
    }

    /**
     * controllListener muuttaa tämän avulla isMoved arvoa
     *
     * @param i liikuttamisen määrä ja suunta
     */
    public synchronized void setIsMoved(int i) {
        this.isMoved += i;
    }

    /**
     * controllListener muuttaa tämän avulla isRotated arvoa
     *
     * @param i pyörittämisen määrä ja suunta
     */
    public synchronized void setIsRotated(int i) {
        this.isRotated += i;
    }

    /**
     * controllListener muuttaa tämän avulla softDrop arvoa
     *
     * @param i
     */
    public synchronized void setSoftDrop(int i) {
        this.softDrop = i;
    }
    /**
     * controllListener muuttaa tämän avulla onko peli pausella
     */
    public synchronized void setIsPaused() {
        this.isPaused = !this.isPaused;
    }

}
