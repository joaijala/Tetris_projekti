/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

import javax.swing.Timer;
import userInterface.gameScreen;

/**
 *
 * @author Järjestelmänvalvoja
 * Tässä luokassa tapahtuu itse pelilooppi ja koko pelin pyörittäminen
 */
public class GameLogic {
    
    private int globalX;
    private int globalY;
    private Tetromino fallingTetromino;
    private Board board;
    private Timer timer;
    private boolean isPaused=false;
    private boolean isTetrominoFalling=false;
    private boolean isGameStarted=false;
    
    public GameLogic(){
        this.board=new Board();
        this.fallingTetromino=new Tetromino();
        timer =new Timer(400, this);

    }
}
