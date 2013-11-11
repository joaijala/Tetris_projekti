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
public class TetrominoTestConstructors {

    public TetrominoTestConstructors() {
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

    /*
     *Testaa että muodostuu uusi palkikka jolla ei ole muotoa
     */
    @Test
    public void testCreatingNewEmptyTetromino() {
        Tetromino tetromino = new Tetromino();
        for (int i = 0; i > 4; i++) {
            assertEquals(0, tetromino.getX(i));
            assertEquals(0, tetromino.getY(i));
        }
    }

    /*
     *Testaa kaikkien muotoisten palikoiden luominen käyttäen nimikonstructoria
     */
    @Test
    public void testCreatingNewEmptyTetrominoByShape() {
        Tetromino tetromino = new Tetromino(Shape.Empty);
        int rightCordinates[][] = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.Empty, tetromino.getShape());
    }

    @Test
    public void testCreatingNewZShapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.ZShape);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.ZShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewSShapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.SShape);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {1, 0}, {1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.SShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewIshapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.IShape);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {0, 1}, {0, 2}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.IShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewTshapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.TShape);
        int rightCordinates[][] = {{-1, 0}, {0, 0}, {1, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.TShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewOShapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.OShape);
        int rightCordinates[][] = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.OShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewLShapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.LShape);
        int rightCordinates[][] = {{-1, -1}, {0, -1}, {0, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.LShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewJShapeTetromino() {
        Tetromino tetromino = new Tetromino(Shape.JShape);
        int rightCordinates[][] = {{1, -1}, {0, -1}, {0, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.JShape, tetromino.getShape());
    }

    /*
     *Testaa kaikkien palikoiden luomista käyttäen numerokonstructoria
     */
    @Test
    public void testCreatingNewEmptyTetrominoNumber() {
        Tetromino tetromino = new Tetromino(0);
        int rightCordinates[][] = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.Empty, tetromino.getShape());
    }

    @Test
    public void testCreatingNewZShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(1);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.ZShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewSShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(2);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {1, 0}, {1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.SShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewIShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(3);
        int rightCordinates[][] = {{0, -1}, {0, 0}, {0, 1}, {0, 2}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.IShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewTShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(4);
        int rightCordinates[][] = {{-1, 0}, {0, 0}, {1, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.TShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewOShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(5);
        int rightCordinates[][] = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.OShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewLShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(6);
        int rightCordinates[][] = {{-1, -1}, {0, -1}, {0, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals(Shape.LShape, tetromino.getShape());
    }

    @Test
    public void testCreatingNewJShapeTetrominoNumber() {
        Tetromino tetromino = new Tetromino(7);
        int rightCordinates[][] = {{1, -1}, {0, -1}, {0, 0}, {0, 1}};
        int[][] cordinates = getCordinateTableOfTetromino(tetromino);
        Assert.assertArrayEquals(rightCordinates, cordinates);
        assertEquals("wrong shape", Shape.JShape, tetromino.getShape());
    }

    @Test
    public void testCreatingDoNothingIfNumberIsWrong() {
        Tetromino tetromino = new Tetromino(8);
        assertEquals(tetromino.getShape(), null);

    }
    /*
     *selvittää tetrominon kordinaatiotaulukon testiä varten
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
