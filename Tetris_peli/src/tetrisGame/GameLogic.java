/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import javax.swing.Timer;
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
    public int isMoved;
    /**
     * Pitää kirjaa onko pelaaja pyörittänyt palikkaa. Se on poitiivinen jos on
     * pyöritetty oikealle ja neggatiivinen jos on pyöritetty oikealle
     */
    public int isRotated;
    private int cleardRows;
    private double timeOfLastDrop;
    private double dropIntervall;
    private GameScreen gameScreen;
    private ControllListener contollistener;

    public GameLogic() {
        this.board = new Board();
        this.fallingTetromino = new Tetromino();
        this.isMoved = 0;
        this.isRotated = 0;
        this.cleardRows = 0;
        this.dropIntervall = 1000;
        this.gameScreen = new GameScreen(this);
        this.contollistener= new ControllListener(this);
    }

    /**
     * gameloop pyöeittää itse pelin pelilooppia
     */
    public void GameLoop() {
        this.isGameStarted = true;
        setNewFallingTetromino();
        /*Pelilooppi*/
        while (this.isGameStarted) {
            /*jos peli on pausella pelilooppi odottaa vain kunnes sitä kunnes joku painaa jatka*/
            if (this.isPaused) {
                return;
            }
            if (this.isTetrominoFalling && getCurrentTimeInMilliseconds() > (this.dropIntervall + this.timeOfLastDrop)) {
                dropOneLineDown();
            }
            if(isMoved!=0){
                if(isMoved>0){
                    if(isMovePossible(this.fallingTetromino,this.globalX+1,this.globalY)){
                        globalX++;
                        isMoved--;
                    }
                }
                if(isMoved<0){
                    if(isMovePossible(this.fallingTetromino,this.globalX-1,this.globalY)){
                        globalX--;
                        isMoved++;
                    }
                }
            }

        }

    }

    /**
     * asettaa tetromiinolle uuden muodon ja laittaa sen pelikentän huipulle
     */
    private void setNewFallingTetromino() {
        this.fallingTetromino.setRandomShape();
        this.globalX = 4;
        this.globalY = 1;
        this.isTetrominoFalling = true;
        this.timeOfLastDrop = getCurrentTimeInMilliseconds();
        gameScreen.repaint();
    }
    /**
     * dropOneLineDown pudottaa tetrominon alaspäin jos se on mahdollista
     */
    private void dropOneLineDown() {
        if (isMovePossible(this.fallingTetromino, this.globalX, this.globalY + 1)) {
            this.globalY++;
            this.timeOfLastDrop = getCurrentTimeInMilliseconds();
            gameScreen.repaint();
        }
        else {
            this.board.setTetrominoToBoard(globalX, globalY, this.fallingTetromino);
            gameScreen.repaint();
            setNewFallingTetromino();
            gameScreen.repaint();
        }
    }
    /**
    * isMovePossible tarkistaa, onko aijottu siirto mahdollinen, jos se ei ole mallinen palautuu false muuten true
    */
    private boolean isMovePossible(Tetromino tetromino, int origoX, int origoY) {
        int[][] boardStatus = board.getBoardStatus();
        for (int i = 0; i < 4; i++) {
            if ((tetromino.getY(i) + origoY) > 19 || (tetromino.getX(i) + globalX) > 9 || (tetromino.getX(i) + globalX) < 0) {
                return false;
            }
            if (boardStatus[(tetromino.getY(i) + origoY)][(tetromino.getX(i) + origoX)] != 0) {
                return false;
            }

        }
        return true;
    }

    /**
     *
     * Tulostaa tippuvan tetrominon kordinaatit pelikentässä,      *
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
    public Tetromino getTetromino() {
        return this.fallingTetromino;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getGlobalX() {
        return this.globalX;
    }

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

}
