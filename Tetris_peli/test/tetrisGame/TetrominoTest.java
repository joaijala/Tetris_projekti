/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

import tetrisGame.Tetromino;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import tetrisGame.Tetromino.Shape;

/**
 *
 * @author Järjestelmänvalvoja
 */
public class TetrominoTest {
    
    public TetrominoTest() {
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
    
    private final int[][][] cordinateTable1Left=new int[][][] { //muotojen kordinaatit yhden vasemmalle kierron jälkeen (tai 3 oikelle)
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { -1, 0 },  { 0, 0 },   { 0, 1  },  { 1, 1 } },
            { { -1, 0 },  { 0, 0 },   { 0, -1 },  { 1, -1 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 2, 0 } },
            { { 0, 1  },  { 0, 0 },   { 0, -1 },  { 1, 0 } },
            { { 0, 0  },  { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, 1 },  { -1, 0 },  { 0, 0 },   { 1, 0 } },
            { { -1, -1 }, { -1, 0 },  { 0, 0 },   { 1, 0 } }
    };
    private final int[][][] cordinateTable2Left=new int[][][] { //muotojen kordinaatit kahden vasemmalle kierron jälkeen (tai 2 oikealle)
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, 1 },   { 0, 0 },   { 1, 0 },   { 1, -1 } },
            { { 0, 1 },   { 0, 0 },   { -1, 0 },  { -1, -1 } },
            { { 0, 1 },   { 0, 0 },   { 0, -1 },  { 0, -2 } },
            { { 1, 0 },   { 0, 0 },   { -1, 0 },  { 0, -1 } },
            { { 0, 0  },  { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { 1, 1 },   { 0, 1 },   { 0, 0 },   { 0, -1 } },
            { { -1, 1 },  { 0, 1 },   { 0, 0 },   { 0, -1 } }
    };
        private final int[][][] cordinateTable3Left=new int[][][] { //muotojen kordinaatit kolmen vasemmalle kierron jälkeen (tai 1 oikelle)
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 1, 0 },   { 0, 0 },   { 0, -1 },  { -1, -1 } },
            { { 1, 0 },   { 0, 0 },   { 0, 1 },   { -1, 1 } },
            { { 1, 0 },   { 0, 0 },   { -1, 0 },  { -2, 0 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { -1, 0 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { 1, -1 },  { 1, 0 },   { 0, 0 },   { -1, 0 } },
            { { 1, 1 },   { 1, 0 },   { 0, 0 },   { -1, 0 } }
    };
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //testaa, että palikat ovat oikein kun niitä on kääntänyt vasemmalle yhden askeleen
    @Test
    public void testRotateLeftWorkFor1Step(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            tetromino.rotateLeft();
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable1Left);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat oikein kun nitä on kääntänyt vasemmalle kaksi askelmaa
    @Test
    public void testRotateLeftWorkFor2Steps(){
            for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotateLeft();
            }
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable2Left);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat samat koko kierroksen jälkeen vase,,aööe
    @Test
    public void testRotateLeftWorkFor1Cycle(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<4; j++){
                tetromino.rotateLeft();
            }
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
        }
        
    }
    
    //testaa, että palikat ovat oikein kun niitä on kääntänyt oikealle yhden askeleen
    @Test
    public void testRotateRightWorkFor1Step(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            tetromino.rotateRight();
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable3Left);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat oikein kun nitä on kääntänyt oikealle kaksi askelmaa
    @Test
    public void testRotateRightWorkFor2Steps(){
            for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotateRight();
            }
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable2Left);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat samat koko kierroksen jälkeen oikealle
    @Test
    public void testRotateRightWorkFor1Cycle(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<4; j++){
                tetromino.rotateRight();
            }
            int [][] expectedCordinates=getCordinatesOfRow(i, cordinateTable);
            int [][] cordinates=getCordinateTableOfTetromino(tetromino);
            Assert.assertArrayEquals(expectedCordinates, cordinates);
            
        }
        
    }
    
    //Testaa, että 2 oikealle on sama kuin kaksi vasemmalle
    @Test
    public void test2LeftEquals2RightRotation(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            Tetromino tetromino2 = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotateRight();
                tetromino2.rotateLeft();
            }
            int [][] cordinatesRight=getCordinateTableOfTetromino(tetromino);
            int [][] cordinatesLeft=getCordinateTableOfTetromino(tetromino2);
            Assert.assertArrayEquals(cordinatesLeft, cordinatesRight);
            
        }
    }
    
    //palauttaa halutun rivin halutusta vastaustaulukosta
    private int[][] getCordinatesOfRow(int rowNumber, int[][][] table){
        int [][] row=new int [4][2];
        for(int i=0;i<4;i++){
            for(int j=0; j<2; j++){
                row[i][j]=table[rowNumber][i][j];
            }
        }
        return row;  
    }

    //palauttaa tetrominon osien kordinaatit
    public int[][] getCordinateTableOfTetromino(Tetromino tetromino){
           int cordinates[][]= new int [4][2];
           for (int i=0; i<4; i++){
                cordinates[i][0]=tetromino.getX(i);
                cordinates[i][1]=tetromino.getY(i);
           }
           return cordinates;
    }
    


    
    
}
