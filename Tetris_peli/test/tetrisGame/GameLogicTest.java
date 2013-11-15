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
        GameLogic game=new GameLogic();
        assertEquals(game.getGlobalX(),0);
        assertEquals(game.getGlobalY(),0);
        assertEquals(game.getClearedRows(),0);
        assertFalse(game.getIsPaused());
        assertFalse(game.getIsGameRunning());
        assertFalse(game.getIsTetrominoFalling());
    }
    @Test
    public void GameLogicConstructorInitialisesEmptyBoard(){
        GameLogic game=new GameLogic();
       
        int[][] boardStatus = game.getBoard().getBoardStatus();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    @Test
    public void GameLogicConstructorIntitialisesNewEmptytetromino(){
        GameLogic game=new GameLogic();
        Tetromino tetromino=game.getTetromino();
        for (int i = 0; i > 4; i++) {
            assertEquals(0, tetromino.getX(i));
            assertEquals(0, tetromino.getY(i));
        }
    }
    /**
     * testaa, että setNewFallingTetromino nollaa kaiken oikein
     */
    @Test
    public void TestSetnewFallingTetromino(){
        GameLogic game=new GameLogic();
        game.setDropDownTrue();
        game.setIsMoved(1);
        game.setIsRotated(1);
        game.setIsRotated(1);
        game.setNewFallingTetromino();
        assertEquals(game.getGlobalX(),4);
        assertEquals(game.getGlobalY(),1);
        assertEquals(game.getIsMoved(),0);
        assertEquals(game.getDropDown(),false);
        assertEquals(game.getIsRotated(),0);
        assertEquals(game.getSoftDrop(),0);
        assertEquals(game.getIsTetrominoFalling(),true);
    }
}