/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import org.junit.After;
import org.junit.Assert;
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
        for(int i=0;i<20;i++){
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
        for(int i=0;i<8;i++){
            Board board=new Board();
            Tetromino tetromino=new Tetromino(i);
            board.setTetrominoToBoard(5, 5, tetromino);
            int [][] boardStatus =board.getBoardStatus();
            for (int j=0; j<4;j++){
                
                    assertEquals(boardStatus[(cordinateTable[i][j][1])+5][(cordinateTable[i][j][0])+5], i);
            }
        }
    }
    
    //Testaa, että chekWhatLinesAreFull pitää kaikk tyhjänä jos buard on tyhjä
    @Test
    public void testChekWhatLinesAreFalseWorkWithEmptyBoard(){
        Board board=new Board();
        board.checkWhatLinesAreFull();
        boolean[] isFull=board.getIsRowFilledStatus();
        for(int i=0;i<20;i++){
            assertEquals(false,isFull[i]);
        }
    }
    
    //Testaa, että chekWhatLinesAreFull ei anna true arvoja kun rivi on puolitäynnä
    @Test
    public void testCheckWhatLinesAreFullWorkWhitHalfullLines(){
        Board board=new Board();
        for(int i=0;i>9;i++){
            board.setNumberToBoard(i, 5, 1);
        }
        board.checkWhatLinesAreFull();
        boolean[] isFull=board.getIsRowFilledStatus();
        for(int i=0;i<20;i++){
            assertEquals(false,isFull[i]);
        }
    }
    //Testaa, että chekWhatLinesAreFull huomaa 1 täysinäisen rivin
    @Test
    public void testCheckWhatLinesAreFullWorkWith1FullLine(){
        Board board=new Board();
        for(int i=0;i<10;i++){
            board.setNumberToBoard(i, 0, 1);
        }
        int rowsFull=board.checkWhatLinesAreFull();
        boolean[] isFull=board.getIsRowFilledStatus();
        assertEquals(true,isFull[0]);
        assertEquals(1,rowsFull);
        for(int i=1;i<20;i++){
            assertEquals(false,isFull[i]);
        }
    }
    //Testaa, että chekWhatLinesAreFull toimii kun kaikki rivit ovat täynnä
    @Test
    public void testCheckWhatLinesAreFullWorkWhenAllRowsAreFull(){
        Board board=new Board();
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                board.setNumberToBoard(j, i, 1);
            }
        }
        int rowsFull=board.checkWhatLinesAreFull();
        boolean[] isFull=board.getIsRowFilledStatus();
        assertEquals(20,rowsFull);
        for(int i=1;i<20;i++){
            assertEquals(true,isFull[i]);
        }
    }
    
    //Testaa, että drop line down toimii 
    @Test
    public void testDropOneLineDownWorks(){
        Board board=new Board();
        Tetromino tetromino=new Tetromino(1);
        board.setTetrominoToBoard(5, 5, tetromino);
        board.dropDownToLine(7);
        int [][] boardStatus =board.getBoardStatus();
        for (int j=0; j<4;j++){
                
             assertEquals(boardStatus[(cordinateTable[1][j][1])+6][(cordinateTable[1][j][0])+5], 1);
        }
    }
    //Testaa että täysinäinen rivi poistuu oikein
    @Test
    public void testRemovingLine(){
        Board board=new Board();
        Tetromino tetromino=new Tetromino(1);
        board.setTetrominoToBoard(5, 5, tetromino);
        for(int i=0;i<10;i++){
            board.setNumberToBoard(i, 7, 1);
        }
        board.checkWhatLinesAreFull();
        board.removeFullLines();
        int [][] expectedBoard=new int[20][10];
        for (int j=0; j<4;j++){
             expectedBoard[(cordinateTable[1][j][1])+6][(cordinateTable[1][j][0])+5]=1;   
        }
        Assert.assertArrayEquals(expectedBoard,board.getBoardStatus());

    }
    //Testaa, että useamman rivin poistaminen kerralla onnistuu
    @Test
    public void TestRemoving2Lines(){
        Board board=new Board();
        Tetromino tetromino=new Tetromino(1);
        board.setTetrominoToBoard(5, 5, tetromino);
        for(int i=0;i<10;i++){
            board.setNumberToBoard(i, 7, 1);
        }
         for(int i=0;i<10;i++){
            board.setNumberToBoard(i, 8, 1);
        }
        board.checkWhatLinesAreFull();
        board.removeFullLines();
        int [][] expectedBoard=new int[20][10];
        for (int j=0; j<4;j++){
             expectedBoard[(cordinateTable[1][j][1])+7][(cordinateTable[1][j][0])+5]=1;   
        }
        Assert.assertArrayEquals(expectedBoard,board.getBoardStatus());
    }
    
    
    

    
}