/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris_game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import tetris_game.Tetromino.Shape;

/**
 *
 * @author Järjestelmänvalvoja
 */
public class TetrominoTest {
    
    public TetrominoTest() {
    }
    private final int[][][] cordinate_table=new int[][][] { //muotojen kordinaatit alutilassa
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };
    
    private final int[][][] cordinate_table_1_left=new int[][][] { //muotojen kordinaatit yhden vasemmalle kierron jälkeen (tai 3 oikelle)
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { -1, 0 },  { 0, 0 },   { 0, 1  },  { 1, 1 } },
            { { -1, 0 },  { 0, 0 },   { 0, -1 },  { 1, -1 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 2, 0 } },
            { { 0, 1  },  { 0, 0 },   { 0, -1 },  { 1, 0 } },
            { { 0, 0  },  { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, 1 },  { -1, 0 },  { 0, 0 },   { 1, 0 } },
            { { -1, -1 }, { -1, 0 },  { 0, 0 },   { 1, 0 } }
    };
    private final int[][][] cordinate_table_2_left=new int[][][] { //muotojen kordinaatit kahden vasemmalle kierron jälkeen (tai 2 oikealle)
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, 1 },   { 0, 0 },   { 1, 0 },   { 1, -1 } },
            { { 0, 1 },   { 0, 0 },   { -1, 0 },  { -1, -1 } },
            { { 0, 1 },   { 0, 0 },   { 0, -1 },  { 0, -2 } },
            { { 1, 0 },   { 0, 0 },   { -1, 0 },  { 0, -1 } },
            { { 0, 0  },  { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { 1, 1 },   { 0, 1 },   { 0, 0 },   { 0, -1 } },
            { { -1, 1 },  { 0, 1 },   { 0, 0 },   { 0, -1 } }
    };
        private final int[][][] cordinate_table_3_left=new int[][][] { //muotojen kordinaatit kolmen vasemmalle kierron jälkeen (tai 1 oikelle)
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
    public void test_rotate_left_work_for_1_step(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            tetromino.rotate_left();
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table_1_left);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat oikein kun nitä on kääntänyt vasemmalle kaksi askelmaa
    @Test
    public void test_rotate_left_work_for_2_steps(){
            for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotate_left();
            }
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table_2_left);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat samat koko kierroksen jälkeen vase,,aööe
    @Test
    public void test_rotate_left_work_for_1_cycle(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<4; j++){
                tetromino.rotate_left();
            }
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
        }
        
    }
    
    //testaa, että palikat ovat oikein kun niitä on kääntänyt oikealle yhden askeleen
    @Test
    public void test_rotate_right_work_for_1_step(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            tetromino.rotate_right();
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table_3_left);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat oikein kun nitä on kääntänyt oikealle kaksi askelmaa
    @Test
    public void test_rotate_right_work_for_2_steps(){
            for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotate_right();
            }
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table_2_left);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
        }
    }
    
    //testaa, että palikat ovat samat koko kierroksen jälkeen oikealle
    @Test
    public void test_rotate_right_work_for_1_cycle(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            for (int j=0; j<4; j++){
                tetromino.rotate_right();
            }
            int [][] expected_cordinates=get_cordinates_of_row(i, cordinate_table);
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(expected_cordinates, cordinates);
            
        }
        
    }
    
    //Testaa, että 2 oikealle on sama kuin kaksi vasemmalle
    @Test
    public void test_2_left_equals_2_right_rotation(){
        for(int i=1;i<=7;i++){
            Tetromino tetromino = new Tetromino(i);
            Tetromino tetromino2 = new Tetromino(i);
            for (int j=0; j<2; j++){
                tetromino.rotate_right();
                tetromino2.rotate_left();
            }
            int [][] cordinates_right=get_cordinate_table_of_tetromino(tetromino);
            int [][] cordinates_left=get_cordinate_table_of_tetromino(tetromino2);
            Assert.assertArrayEquals(cordinates_left, cordinates_right);
            
        }
    }
    
    //palauttaa halutun rivin halutusta vastaustaulukosta
    private int[][] get_cordinates_of_row(int row_number, int[][][] table){
        int [][] row=new int [4][2];
        for(int i=0;i<4;i++){
            for(int j=0; j<2; j++){
                row[i][j]=table[row_number][i][j];
            }
        }
        return row;  
    }

    //palauttaa tetrominon osien kordinaatit
    public int[][] get_cordinate_table_of_tetromino(Tetromino tetromino){
           int cordinates[][]= new int [4][2];
           for (int i=0; i<4; i++){
                cordinates[i][0]=tetromino.get_X(i);
                cordinates[i][1]=tetromino.get_Y(i);
           }
           return cordinates;
    }
    


    
    
}
