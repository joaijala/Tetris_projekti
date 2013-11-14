/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaijala
 */
public class GameLogicTest {
    
    public GameLogicTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /*
     * Test that gameLogic constructor works correctly (3 tests)
     */

    @Test
    public void GameLogicConstructorsInitialisesAllToZero(){
        GameLogic game=new GameLogic(null);
        assertEquals(game.getGlobalX(),0);
        assertEquals(game.getGlobalY(),0);
        assertEquals(game.getClearedRows(),0);
        assertFalse(game.getIsPaused());
        assertFalse(game.getIsGameRunning());
        assertFalse(game.getIsTetrominoFalling());
    }
    @Test
    public void GameLogicConstructorInitialisesEmptyBoard(){
        GameLogic game=new GameLogic(null);
       
        int[][] boardStatus = game.getBoard().getBoardStatus();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    @Test
    public void GameLogicConstructorIntitialisesNewEmptytetromino(){
        Tetromino tetromino = new Tetromino();
        for (int i = 0; i > 4; i++) {
            assertEquals(0, tetromino.getX(i));
            assertEquals(0, tetromino.getY(i));
        }
    }
}