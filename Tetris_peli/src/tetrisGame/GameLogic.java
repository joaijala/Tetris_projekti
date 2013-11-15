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
    private final Tetromino fallingTetromino;
    private final Board board;
    private boolean isPaused = false;
    private boolean isTetrominoFalling = false;
    private boolean isGameRunning = false;
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
    /**
     * dropDown antaa pelaajan tiputtaa tetrominon kokonaan alas
     *
     */
    private boolean dropDown;
    private int cleardRows;
    private double timeOfLastDrop;
    private double dropIntervall;
    private GameScreen gameScreen;
    private ControllListener contollistener;

    public GameLogic() {
        this.board = new Board();
        this.fallingTetromino = new Tetromino();
        this.cleardRows = 0;
        this.dropIntervall = 200;
        this.gameScreen = new GameScreen(this);

    }

    /**
     * gameloop pyöeittää itse pelin pelilooppia
     */
    public void GameLoop() {

        this.isGameRunning = true;
        setNewFallingTetromino();
        /*Pelilooppi*/
        while (this.isGameRunning) {

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
            /**
             * jos pelaaja on painanut dropDown tetromino tippuu alas kunnes se
             * ei voi enää tippuu alas
             */
            if (this.dropDown) {
                while (dropDown) {
                    dropOneLineDown();
                }
            }

        }

    }

    /**
     * asettaa tetromiinolle uuden muodon ja laittaa sen pelikentän huipulle,
     * sekä nollaa liikuttamiseen liittyvät muututjat
     */
    public void setNewFallingTetromino() {
        this.globalX = 4;
        this.globalY = 1;
        this.fallingTetromino.setRandomShape();
        /*peli loppuu jos ei voi laittaa uutta palikkaa*/
        if (!isMovePossible(this.fallingTetromino, globalX, globalY)) {
            this.isGameRunning = false;
        }
        this.isMoved = 0;
        this.isRotated = 0;
        this.softDrop = 0;
        this.dropDown = false;
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
            if (isMovePossible(fallingTetromino, globalX, globalY)) {
                this.gameScreen.repaint();
                this.isRotated--;
                return;
            }
            else if (!doWallKick()) {
                this.fallingTetromino.rotateLeft();
            }
            this.gameScreen.repaint();
            this.isRotated--;

        }
        else if (this.isRotated < 0) {
            this.fallingTetromino.rotateLeft();
            if (isMovePossible(fallingTetromino, globalX, globalY)) {
                this.gameScreen.repaint();
                this.isRotated++;
                return;
            }
            else if (!doWallKick()) {
                this.fallingTetromino.rotateRight();
            }
            this.gameScreen.repaint();
            this.isRotated++;

        }

    }

    private boolean doWallKick() {
        /*try wallKick right*/
        if (tryWallKick(1)) {
            this.globalX++;
            return true;

        }
        /*try wallKick left*/
        if (tryWallKick(-1)) {
            this.globalX--;
            return true;
        }
        /* if the tetromino is I shape try wallKick with 2*/

        if (this.fallingTetromino.getShape().ordinal() == 3 && tryWallKick(2)) {
            this.globalX += 2;
            return true;
        }

        if (this.fallingTetromino.getShape().ordinal() == 3 && tryWallKick(-2)) {
            this.globalX -= 2;
            return true;
        }

        return false;
    }

    /**
     * Tries wallKick. WallKick means it when the tetromino can rotate when it
     * is against the wall by kicking of the wall
     *
     * @param direction and amount of wall kick;
     */
    private boolean tryWallKick(int i) {
        if (isMovePossible(this.fallingTetromino, globalX + i, globalY)) {
            return true;
        }
        return false;
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
        boolean isTrue = true;
        if (origoX > 9) {
            return false;
        }
        for (int i = 0; i < 4; i++) {

            if (((tetromino.getY(i) + origoY) > 19) || ((tetromino.getX(i) + origoX) > 9) || ((tetromino.getX(i) + origoX) < 0)) {
                return false;
            }
            if (boardStatus[(tetromino.getY(i) + origoY)][(tetromino.getX(i) + origoX)] != 0) {
                return false;
            }

        }
        return isTrue;
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
     * returns the amount of cleared rows
     * @return clearedRows
     */
    public int getClearedRows(){
        return this.cleardRows;
    }
    /**
     * returns wheter the game is paused or not
     * @return isPaused
     */
    public boolean getIsPaused(){
        return this.isPaused;
    }
    /**
     * returns if the game is running
     * @return isGameRunning
     */
    public boolean getIsGameRunning(){
        return this.isGameRunning;
    }
    /**
     * returns wheter the tetromino is falling
     * @return isTetrominoFalling
     */
    public boolean getIsTetrominoFalling(){
        return this.isTetrominoFalling;
    }
    public boolean getDropDown(){
        return this.dropDown;
    }
    public GameScreen getGameScreen(){
        return this.gameScreen;
    }
    public int getIsMoved(){
        return this.isMoved;
    }
    public int getIsRotated(){
        return this.isRotated;
    }
    public int getSoftDrop(){
        return this.softDrop;
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
        this.isMoved = i;
    }

    /**
     * controllListener muuttaa tämän avulla isRotated arvoa
     *
     * @param i pyörittämisen määrä ja suunta
     */
    public synchronized void setIsRotated(int i) {
        this.isRotated = i;
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

    /**
     * ControllListener muuttaa tämän avulla tartteeko pala tiputtaa alas
     */
    public synchronized void setDropDownTrue() {
        this.dropDown = true;
    }

}
