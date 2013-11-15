/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetrisGame.Tetromino.Shape;

/**
 * tässä on testattuna gameLogikin metodit, jotia gameLoop käyttä. Itse
 * GameLooppin testaan käsin
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
    public void GameLogicConstructorsInitialisesAllToZero() {
        GameLogic game = new GameLogic();
        assertEquals(game.getGlobalX(), 0);
        assertEquals(game.getGlobalY(), 0);
        assertEquals(game.getClearedRows(), 0);
        assertFalse(game.getIsPaused());
        assertFalse(game.getIsGameRunning());
        assertFalse(game.getIsTetrominoFalling());
    }

    @Test
    public void GameLogicConstructorInitialisesEmptyBoard() {
        GameLogic game = new GameLogic();

        int[][] boardStatus = game.getBoard().getBoardStatus();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }

    @Test
    public void GameLogicConstructorIntitialisesNewEmptytetromino() {
        GameLogic game = new GameLogic();
        Tetromino tetromino = game.getTetromino();
        for (int i = 0; i > 4; i++) {
            assertEquals(0, tetromino.getX(i));
            assertEquals(0, tetromino.getY(i));
        }
    }

    /**
     * testaa, että setNewFallingTetromino nollaa kaiken oikein
     */
    @Test
    public void TestSetnewFallingTetromino() {
        GameLogic game = new GameLogic();
        game.setDropDownTrue();
        game.setIsMoved(1);
        game.setIsRotated(1);
        game.setIsRotated(1);
        game.setNewFallingTetromino();
        assertEquals(game.getGlobalX(), 4);
        assertEquals(game.getGlobalY(), 1);
        assertEquals(game.getIsMoved(), 0);
        assertEquals(game.getDropDown(), false);
        assertEquals(game.getIsRotated(), 0);
        assertEquals(game.getSoftDrop(), 0);
        assertEquals(game.getIsTetrominoFalling(), true);
    }

    /**
     * testaa, että dropOneLineDown toimii kun alla ei ole mitään
     */
    @Test
    public void TestDropOneLineDownWithNothingUnder() {
        GameLogic game = new GameLogic();
        game.setNewFallingTetromino();
        game.dropOneLineDown();
        assertEquals(game.getGlobalY(), 2);
    }

    /**
     * testaa, että dropOneLindeDown toimii oikein kun tetromino osuu pohjaan
     */
    @Test
    public void TestDropOneLineDownWorkWhenTetrominoHitBottom() {
        GameLogic game = new GameLogic();
        game.setNewFallingTetromino();
        Tetromino tetromino = game.getTetromino();
        int shape = tetromino.getShape().ordinal();
        int cordinates[][] = getCordinateTableOfTetromino(tetromino);
        game.dropOneLineDown();
        while (game.getGlobalY() != 1) {//kun globalY on 1 peli on luonut uuden tetrominon ylös
            game.dropOneLineDown();
        }
        int[][] board = game.getBoard().getBoardStatus();

        for (int i = 0; i < 4; i++) {
            assertEquals(board[18 + cordinates[i][1]][4 + cordinates[i][0]], shape);
        }
    }
    /**
     * testaa Että DropOneLineDown toimii kun se pysähtyy alla olevaan tetrominoon
     */
    @Test
    public void TestDropOneLineDownWorkWhenDroppingOnTetrominoOnBoard(){
        GameLogic game = new GameLogic();
        game.getBoard().setTetrominoToBoard(4, 18, new Tetromino(3));
        game.getBoard().setTetrominoToBoard(3, 18, new Tetromino(3));
        game.getBoard().setTetrominoToBoard(5, 18, new Tetromino(3));
        game.setNewFallingTetromino();
        Tetromino tetromino = game.getTetromino();
        int shape = tetromino.getShape().ordinal();
        int cordinates[][] = getCordinateTableOfTetromino(tetromino);
        game.dropOneLineDown();
        while (game.getGlobalY() != 1) {//kun globalY on 1 peli on luonut uuden tetrominon ylös
            game.dropOneLineDown();
        }
        int[][] board = game.getBoard().getBoardStatus();

        for (int i = 0; i < 4; i++) {
            assertEquals(board[15+ cordinates[i][1]][4 + cordinates[i][0]], shape);
        }
    }
    @Test
    public void testTakecareOfFullLinesWorks(){
        GameLogic game = new GameLogic();
        Board board = game.getBoard();
        for (int i = 0; i < 10; i++) {
            board.setNumberToBoard(i, 10, 1);
        }
        game.takeCareOfFullLines();
        int[][] boardStatus = game.getBoard().getBoardStatus();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(boardStatus[i][j], 0);
            }
        }
        assertEquals(game.getClearedRows(),1);
        
    }
    /**
     * 
     * @param tetromino
     * @return tetrominon sisäiset kordinaatit taulukossa
     */
    public int[][] getCordinateTableOfTetromino(Tetromino tetromino) {
        int cordinates[][] = new int[4][2];
        for (int i = 0; i < 4; i++) {
            cordinates[i][0] = tetromino.getX(i);
            cordinates[i][1] = tetromino.getY(i);
        }
        return cordinates;
    }
}
