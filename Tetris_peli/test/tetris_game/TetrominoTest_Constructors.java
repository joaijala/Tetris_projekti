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
public class TetrominoTest_Constructors {
    
    public TetrominoTest_Constructors() {
    }
    
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

    //Testaa että muodostuu uusi palkikka jolla ei ole muotoa
    @Test
    public void test_creating_new_empty_Tetromino(){
        Tetromino tetromino =new Tetromino();
        for (int i=0; i>4; i++){
            assertEquals(0,tetromino.get_X(i));
            assertEquals(0,tetromino.get_Y(i));
        }  
    }
    
    //Testaa kaikkien muotoisten palikoiden luominen käyttäen nimikonstructoria
    @Test
    public void test_creating_new_Empty_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.Empty);
            int right_cordinates[][]={ { 0, 0},  { 0, 0 },   { 0, 0 },   { 0, 0 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.Empty,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_Z_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.Z_shape);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.Z_shape,tetromino.get_Shape());        
    }
    
    @Test
    public void test_creating_new_S_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.S_shape);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.S_shape,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_I_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.I_shape);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.I_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_T_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.T_shape);
            int right_cordinates[][]={ { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.T_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_O_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.O_shape);
            int right_cordinates[][]={ { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.O_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_L_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.L_shape);
            int right_cordinates[][]={ { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.L_shape,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_J_shape_Tetromino(){
            Tetromino tetromino= new Tetromino(Shape.J_shape);
            int right_cordinates[][]={ { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.J_shape,tetromino.get_Shape());      
    }
    
    
    //Testaa kaikkien palikoiden luomista käyttäen numerokonstructoria
    
    @Test
    public void test_creating_new_Empty_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(0);
            int right_cordinates[][]={ { 0, 0},  { 0, 0 },   { 0, 0 },   { 0, 0 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.Empty,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_Z_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(1);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.Z_shape,tetromino.get_Shape());        
    }
    
    @Test
    public void test_creating_new_S_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(2);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.S_shape,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_I_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(3);
            int right_cordinates[][]={ { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.I_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_T_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(4);
            int right_cordinates[][]={ { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.T_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_O_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(5);
            int right_cordinates[][]={ { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.O_shape,tetromino.get_Shape());      
    }
    
        @Test
    public void test_creating_new_L_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(6);
            int right_cordinates[][]={ { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals(Shape.L_shape,tetromino.get_Shape());      
    }
    
    @Test
    public void test_creating_new_J_shape_Tetromino_Number(){
            Tetromino tetromino= new Tetromino(7);
            int right_cordinates[][]={ { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } };
            int [][] cordinates=get_cordinate_table_of_tetromino(tetromino);
            Assert.assertArrayEquals(right_cordinates, cordinates);
            assertEquals("wrong shape",Shape.J_shape,tetromino.get_Shape());      
    }
    // selvittää tetrominon kordinaatiotaulukon testiä varten
    public int[][] get_cordinate_table_of_tetromino(Tetromino tetromino){
           int cordinates[][]= new int [4][2];
           for (int i=0; i<4; i++){
                cordinates[i][0]=tetromino.get_X(i);
                cordinates[i][1]=tetromino.get_Y(i);
           }
           return cordinates;
    }
}
