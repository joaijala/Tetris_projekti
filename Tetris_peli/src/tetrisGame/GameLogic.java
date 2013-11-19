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

    private final int[] scoresFromRow = {0, 40, 100, 300, 1200};
    private static final double NANOSEC_TO_MILLISEC = 0.000001;
    private int globalX;
    private int globalY;
    private final Tetromino fallingTetromino;
    private final Tetromino nextTetromino;
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
     * tells the number of soft drops done for tetormino
     */
    private int softDropsDone;
    /**
     * dropDown antaa pelaajan tiputtaa tetrominon kokonaan alas
     *
     */
    private boolean dropDown;
    private int clearedRows;
    private double dropIntervall;
    private int level;
    private int score;
    private double timeOfLastDrop;
    private GameScreen gameScreen;
    private ControllListener contollistener;

    public GameLogic() {
        this.board = new Board();
        this.fallingTetromino = new Tetromino();
        this.nextTetromino = new Tetromino();
        this.nextTetromino.setRandomShape();
        this.clearedRows = 0;
        this.dropIntervall = 500;
        this.gameScreen = new GameScreen(this);
        this.level = 0;
        this.score = 0;

    }

    /**
     * gameloop pyöeittää itse pelin pelilooppia
     */
    public void gameLoop() {

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
                this.softDropsDone++;
                softDrop--;
            }
            /**
             * jos pelaaja on painanut dropDown tetromino tippuu alas kunnes se
             * ei voi enää tippuu alas
             */
            if (this.dropDown) {
                while (dropDown) {
                    dropOneLineDown();
                    this.softDropsDone++;
                }
            }

        }

    }

    /**
     * asettaa fallingTetromiinolle seuraavan Tetrominon muodon,
     * nexttetrominolle uuden random muodon sekä nollaa liikuttamiseen liittyvät
     * muututjat
     */
    public void setNewFallingTetromino() {
        this.globalX = 4;
        this.globalY = 1;
        this.fallingTetromino.setShape(this.nextTetromino.getShape());
        this.nextTetromino.setRandomShape();
        /*peli loppuu jos ei voi laittaa uutta palikkaa*/
        if (!isMovePossible(this.fallingTetromino, globalX, globalY)) {
            this.isGameRunning = false;
        }
        this.isMoved = 0;
        this.isRotated = 0;
        this.softDrop = 0;
        this.softDropsDone = 0;
        this.dropDown = false;
        this.isTetrominoFalling = true;
        this.timeOfLastDrop = getCurrentTimeInMilliseconds();
        gameScreen.repaint();
    }

    /**
     * dropOneLineDown pudottaa tetrominon alaspäin jos se on mahdollista,
     * muuten asettaa uuden tetrominon
     */
    public void dropOneLineDown() {
        if (isMovePossible(this.fallingTetromino, this.globalX, this.globalY + 1)) {
            this.globalY++;
            this.timeOfLastDrop = getCurrentTimeInMilliseconds();
            gameScreen.repaint();
        } else {
            this.isTetrominoFalling = false;
            this.board.setTetrominoToBoard(globalX, globalY, this.fallingTetromino);
            takeCareOfFullLines();
            setNewFallingTetromino();
            gameScreen.repaint();
        }
    }

    /**
     * Huolehtii täysinäisten rivien tarkistamisesta ja niiden poistamisesta
     *
     */
    public void takeCareOfFullLines() {
        int fullLines = this.board.checkWhatLinesAreFull();
        addScores(fullLines);
        if (fullLines != 0) {
            this.board.removeFullLines();
            this.clearedRows += fullLines;
            seIfLevelChange();
        }
    }

    /**
     * Antaa pisteitä aina kun pala on tippunut valmiiksi
     *
     * @param removedLines
     */
    public void addScores(int removedLines) {
        int scores = this.scoresFromRow[removedLines];
        this.score += this.softDropsDone + ((this.level + 1) * scores);
    }

    /**
     * jos levelin vaihdon kriteeri täyttyy leveli vaihtuu ja vauti nopeutuu
     */
    public void seIfLevelChange() {
        if (this.clearedRows >= (this.level + 1) * 10 && this.level < 11) {
            this.level++;
            this.dropIntervall -= 50;
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
     * uusi rotate tetromino
     * käsittelee tetrominon pyörittämistä
     */
    public void rotateTetromino() {

        if (this.isRotated > 0) {
            this.fallingTetromino.rotateRight();
            if (!isMovePossible(fallingTetromino, globalX, globalY)) {
                if (!manageWallKick()) {
                    this.fallingTetromino.rotateLeft();
                } 
            }
            this.isRotated--;
        }
        else if (this.isRotated < 0) {
            this.fallingTetromino.rotateLeft();
            if (!isMovePossible(fallingTetromino, globalX, globalY)) {
                if (!manageWallKick()) {
                    this.fallingTetromino.rotateRight();
                }
            }
            this.isRotated++;
        }
        this.gameScreen.repaint();

    }
    /*    /**
     * vanha rotatetetromino
     /
     public void rotateTetromino() {

     if (this.isRotated > 0) {
     this.fallingTetromino.rotateRight();
     if (isMovePossible(fallingTetromino, globalX, globalY)) {
     this.gameScreen.repaint();
     this.isRotated--;
     return;
     }
     else if (!manageWallKick()) {
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
     else if (!manageWallKick()) {
     this.fallingTetromino.rotateRight();
     }
     this.gameScreen.repaint();
     this.isRotated++;

     }

     }*/

    private boolean doWallKick(int amount) {
        /*try wallKick right*/
        if (tryWallKick(amount)) {
            this.globalX += amount;
            return true;

        } /*try wallKick left*/ else if (tryWallKick(-amount)) {
            this.globalX -= amount;
            return true;
        }
        return false;
    }

    private boolean manageWallKick() {

        if (doWallKick(1)) {
            return true;
        } else if (this.fallingTetromino.getShape().ordinal() == 3 && doWallKick(2)) {
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
     * Tulostaa tippuvan tetrominon kordinaatit pelikentässä, * private void
     * printFallingTetromino(){ for(int i=0;i<4;i++){
     * System.out.println((this.fallingTetromino.getX(i)+globalX)+",
     * "+(this.fallingTetromino.getY(i)+globalY)); } System.out.println(""); }
     * private void printBoard(){ int [][] boardStatus=board.getBoardStatus();
     * for (int i=0;i<20;i++){ for(int j=0;j<10;j++){
     * System.out.print(boardStatus[i][j]+", "); } System.out.println(""); } }
     */
    /**
     *
     * @return palauttaa juuri tippuvan tetrominon
     */
    public Tetromino getFallingTetromino() {
        return this.fallingTetromino;
    }

    /**
     *
     * @return palauttaa seuraavan tetorminon
     */
    public Tetromino getNextTetromino() {
        return this.nextTetromino;
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
     * returns wheter the game is paused or not
     *
     * @return isPaused
     */
    public boolean getIsPaused() {
        return this.isPaused;
    }

    /**
     * returns if the game is running
     *
     * @return isGameRunning
     */
    public boolean getIsGameRunning() {
        return this.isGameRunning;
    }

    /**
     * returns wheter the tetromino is falling
     *
     * @return isTetrominoFalling
     */
    public boolean getIsTetrominoFalling() {
        return this.isTetrominoFalling;
    }

    /**
     * returns status of drop down
     *
     * @return dropDown
     */
    public boolean getDropDown() {
        return this.dropDown;
    }

    /**
     * returns game screen
     *
     * @return gameScreen
     */

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    /**
     *
     * @return isMoved
     */
    public int getIsMoved() {
        return this.isMoved;
    }

    /**
     *
     * @return isRotated
     */

    public int getIsRotated() {
        return this.isRotated;
    }

    /**
     *
     * @return softDrop
     */

    public int getSoftDrop() {
        return this.softDrop;
    }

    /**
     *
     * @return getlevel
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return getScore
     */
    public int getScore() {
        return this.score;
    }

    /**
     * returns the amount of cleared rows
     *
     * @return clearedRows
     */
    public int getClearedRows() {
        return this.clearedRows;
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
