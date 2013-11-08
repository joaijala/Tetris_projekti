/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetrisGame.Board;

/**
 *
 * @author joaijala
 */
public class BoardTest {
    
    public BoardTest() {
    }
        private final int[][][] cordinateTable=new int[][][] { //muotojen kordinaatit alutilassa
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //Testaa, että boardi on alustettu nollaan (0)
    @Test
    public void testNewCreatedBoardIsEmpty(){
        Board board=new Board();
        int [][] boardStatus =board.getBoardStatus();
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    //testaa, että isRowFilled on false joka rivillä
    @Test
    public void testNewBoardNoRowFilled(){
        Board board=new Board();
        boolean [] isRowFull=board.getIsRowFilledStatus();
        for(int i=0;i<19;i++){
            assertEquals(false, isRowFull[i]);
        }
    }
    //testaa, että setNumberToBoard ei tee mitään, jos x on liian suuri tai pieni
    @Test
    public void setNumberToBoardDoNothinIfXIsOutsideOfBoundaries(){
        Board board=new Board();
        board.setNumberToBoard(-1, 5, 1);
        board.setNumberToBoard(10, 5, 1);
        int [][] boardStatus =board.getBoardStatus();
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    //testaa, että setNumberToBoard ei tee mitään, jos y on liian suuri tai pieni
    @Test
    public void setNumberToBoardDoNothingIfYIsOutsideOfBoundaries(){
        Board board=new Board();
        board.setNumberToBoard(1, -1, 1);
        board.setNumberToBoard(1, 20, 1);
        int [][] boardStatus =board.getBoardStatus();
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    
    //testaa, että setNumberToBoard toimii kulmiin/rajoihin
    @Test
    public void setNumberToBoardWorkInCorners(){
        Board board=new Board();
        board.setNumberToBoard(0, 0, 1);
        board.setNumberToBoard(9, 0, 1);
        board.setNumberToBoard(0, 19, 1);
        board.setNumberToBoard(9, 19, 1);
        int [][] boardStatus =board.getBoardStatus();
        assertEquals(boardStatus[0][0], 1);
        assertEquals(boardStatus[0][9], 1);
        assertEquals(boardStatus[19][0], 1);
        assertEquals(boardStatus[19][9], 1);
    }
    
    //testaa, että setNumberToBoard ei tee mitään jos numero ei vastaa Shape eunumin arvoija
    @Test
    public void setNumberToBoardDoNothingIfValueOutsideOfBoundaries(){
        Board board=new Board();
        board.setNumberToBoard(0, 0, 8);
        board.setNumberToBoard(1, 1, -1);
        int [][] boardStatus =board.getBoardStatus();
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                assertEquals(boardStatus[i][j], 0);
            }
        }
    }
    //testaa, että tetrominon asettaminnen boardiin onnistuu
    @Test
    public void testSetTetrominoToBoardVork(){
        for(int i=0;i<7;i++){
            Board board=new Board();
            Tetromino tetromino=new Tetromino(i);
            board.setTetrominoToBoard(5, 5, tetromino);
            int [][] boardStatus =board.getBoardStatus();
            for (int j=0; j<4;j++){
                
                    assertEquals(boardStatus[(cordinateTable[i][j][1])+5][(cordinateTable[i][j][0])+5], i);
            }
        }
    }

    
}