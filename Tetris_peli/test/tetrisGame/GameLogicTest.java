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

/**
 * tässä on testattuna gameLogikin metodit, jotia gameLoop käyttä. Itse
 * GameLooppin testaan käsin
 *
 * @author joaijala
 */
public class GameLogicTest {
    

    public GameLogicTest() {
    }
        private final int[][][] cordinateTable = new int[][][]{ /*muotojen kordinaatit alutilassa*/
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{1, -1}, {1, 0}, {0, 0}, {0, 1}},
        {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
        {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
        {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, -1}, {1, -1}, {1, 0}, {1, 1}},
        {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
    };

    private final int[][][] cordinateTable1Left = new int[][][]{ /*muotojen kordinaatit yhden vasemmalle kierron jälkeen (tai 3 oikelle)*/
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{-1, -1}, {0, -1}, {0, 0}, {1, 0}},
        {{-1, 0}, {0, 0}, {0, -1}, {1, -1}},
        {{-1, 0}, {0, 0}, {1, 0}, {2, 0}},
        {{0, 1}, {0, 0}, {0, -1}, {1, 0}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}},
        {{-1, -1}, {-1, 0}, {0, 0}, {1, 0}}
    };
    private final int[][][] cordinateTable2Left = new int[][][]{ /*muotojen kordinaatit kahden vasemmalle kierron jälkeen (tai 2 oikealle)*/
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{-1, 1}, {-1, 0}, {0, 0}, {0, -1}},
        {{0, 1}, {0, 0}, {-1, 0}, {-1, -1}},
        {{0, 1}, {0, 0}, {0, -1}, {0, -2}},
        {{1, 0}, {0, 0}, {-1, 0}, {0, -1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}},
        {{-1, 1}, {0, 1}, {0, 0}, {0, -1}}
    };
    private final int[][][] cordinateTable3Left = new int[][][]{ /*muotojen kordinaatit kolmen vasemmalle kierron jälkeen (tai 1 oikelle)*/
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{1, 1}, {0, 1}, {0, 0}, {-1, 0}},
        {{1, 0}, {0, 0}, {0, 1}, {-1, 1}},
        {{1, 0}, {0, 0}, {-1, 0}, {-2, 0}},
        {{0, -1}, {0, 0}, {0, 1}, {-1, 0}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{1, 0}, {1, 1}, {0, 1}, {-1, 1}},
        {{1, 1}, {1, 0}, {0, 0}, {-1, 0}}
    };

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
        assertEquals(game.getScore(), 0);
        assertEquals(game.getLevel(), 0);
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
        Tetromino tetromino = game.getFallingTetromino();
        for (int i = 0; i > 4; i++) {
            assertEquals(0, tetromino.getX(i));
            assertEquals(0, tetromino.getY(i));
        }
        tetromino = game.getNextTetromino();
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
        assertEquals(game.getIsRotated(), 0);
        assertEquals(game.getSoftDrop(), 0);
        assertEquals(game.getIsTetrominoFalling(), true);
    }
    /**
     * testaa että SetnewFallingTetromino laittaa uudeksi fallingTetrominoksi vanhan nexttetromino
     */
    @Test
    public void TestSetNewFallingTetrominoSetsNextTetrominoToFallingTetromino(){
        GameLogic game = new GameLogic();
        int shape =game.getNextTetromino().getShape().ordinal();
        game.setNewFallingTetromino();
        assertEquals(shape, game.getFallingTetromino().getShape().ordinal());
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
        //testi tehdääm iseamman kerran, jotta kaikki tilantet testataan
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            Tetromino tetromino = game.getFallingTetromino();
            int shape = tetromino.getShape().ordinal();
            int cordinates[][] = getCordinateTableOfTetromino(tetromino);
            game.dropOneLineDown();
            while (game.getGlobalY() != 1) {//kun globalY on 1 peli on luonut uuden tetrominon ylös
                game.dropOneLineDown();
            }
            int[][] board = game.getBoard().getBoardStatus();
            if (shape != 3) {
                for (int i = 0; i < 4; i++) {
                    assertEquals(board[18 + cordinates[i][1]][4 + cordinates[i][0]], shape);
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    assertEquals(board[17 + cordinates[i][1]][4 + cordinates[i][0]], shape);
                }
            }
        }
    }

    /**
     * testaa Että DropOneLineDown toimii kun se pysähtyy alla olevaan
     * tetrominoon
     */
    @Test
    public void TestDropOneLineDownWorkWhenDroppingOnTetrominoOnBoard() {
        GameLogic game = new GameLogic();
        game.getBoard().setTetrominoToBoard(4, 18, new Tetromino(3));
        game.getBoard().setTetrominoToBoard(3, 18, new Tetromino(3));
        game.getBoard().setTetrominoToBoard(5, 18, new Tetromino(3));
        game.setNewFallingTetromino();
        Tetromino tetromino = game.getFallingTetromino();
        int shape = tetromino.getShape().ordinal();
        int cordinates[][] = getCordinateTableOfTetromino(tetromino);
        game.dropOneLineDown();
        while (game.getGlobalY() != 1) {//kun globalY on 1 peli on luonut uuden tetrominon ylös
            game.dropOneLineDown();
        }
        int[][] board = game.getBoard().getBoardStatus();

        for (int i = 0; i < 4; i++) {
            assertEquals(board[15 + cordinates[i][1]][4 + cordinates[i][0]], shape);
        }
    }

    /**
     * testaa, että täysinäiset rivit poistuvat oikein, (tätä toiminnallisuutta
     * on testattu enemmän board luokan testeissä)
     */
    @Test
    public void testTakeCareOfFullLinesWorks() {
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
        assertEquals(game.getClearedRows(), 1);

    }
    /**
     * Testataan, että leveli on 1 kun poistetaan 10 riviä
     * Huom kaikkii 10 rivii ei voi poistaa kerralla
     */
    @Test
    public void TestIfLevelChangeWhen10RowsAreRemoved(){
        GameLogic game = new GameLogic();
        Board board = game.getBoard();
        for (int i = 15; i < 19; i++) {
            for(int j=0;j<10;j++){
                board.setNumberToBoard(j, i, 1);
            }
        }
        game.takeCareOfFullLines();
        for (int i = 15; i < 19; i++) {
            for(int j=0;j<10;j++){
                board.setNumberToBoard(j, i, 1);
            }
        }
        game.takeCareOfFullLines();
        for (int i = 17; i < 19; i++) {
            for(int j=0;j<10;j++){
                board.setNumberToBoard(j, i, 1);
            }
        }
        game.takeCareOfFullLines();
        assertEquals(game.getLevel(),1);
        assertEquals(game.getClearedRows(),10);
    }

    /**
     * isMovePossiblea en testaa erikseen, sillä se testaantuu samalla
     * dropOneLineDown moveTetromino ja rotateTetromino
     */
    /**
     * Testaa moveTetromino kun liikkuminen on mahdollista
     *
     */
    @Test
    public void TestMovetetrominoRight() {
        GameLogic game = new GameLogic();
        game.setNewFallingTetromino();
        game.setIsMoved(1);
        game.moveTetromino();
        assertEquals(game.getGlobalX(), 5);
    }

    @Test
    public void TestMovetetrominoLeft() {
        GameLogic game = new GameLogic();
        game.setNewFallingTetromino();
        game.setIsMoved(-1);
        game.moveTetromino();
        assertEquals(game.getGlobalX(), 3);
    }

    /**
     * Testaa moveTetromino kun tetromino on jo pelilaudan reunassa
     */
    @Test
    public void testMoveTetrominoWhenTetrominoIsAtLeftBorder() {
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,-1);
            if (game.getFallingTetromino().getShape().ordinal() != 4) {
                testMoveTetrominoAtBoarder(game,-1,0);
            }
            else {
                testMoveTetrominoAtBoarder(game,-1,1);
            }
        }
    }
    @Test
    public void testMoveTetrominoWhenTetrominoIsAtRightBorder() {
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,1);
            if (game.getFallingTetromino().getShape().ordinal() == 3) {
                testMoveTetrominoAtBoarder(game,1,9);
            }
            else {
                testMoveTetrominoAtBoarder(game,1,8);
            }
        }
    }
    /**
     * seuraavaksi testauksia joistakin tetrominon pyöritystapauksista
     */
    /**
     * testaa, että rotaatio toimii kun paliikkaa pyöritetään oikealle spavnauskohdassa
     */
    @Test
    public void testRotateTetrominoRight(){
        for(int i=0;i<100;i++){
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            testRotatingTetromino(game,1,3);
            if(game.getFallingTetromino().getShape().ordinal()!=3){
                testRotatingTetromino(game,1,2);
            }
            else{// jos tetromino on spawnauskohdassa sitä ei voi pyörittää 2 kertaa oikealle ilman, että se osuu kattoon
                testRotatingTetromino(game,1,3);
            }
        }
    }
    /**
     * testaa, että rotaatio toimii spavnauspaikassa vasemmalle
     */
    @Test
    public void testRotateTetrominoLeft(){
        for(int i=0;i<100;i++){
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            testRotatingTetromino(game,-1,1);
            if(game.getFallingTetromino().getShape().ordinal()!=3){
                testRotatingTetromino(game,-1,2);
            }
            else{// jos tetromino on spawnauskohdassa sitä ei voi pyörittää 2 kertaa oikealle ilman, että se osuu kattoon
                testRotatingTetromino(game,-1,1);
            }
        }
    }
    /**
     * testaa, että pyöritys oikealle toimii oikean seinän vieressä
     * samalla wallKick testaantuu
     * 
     */
    @Test
    public void TestRotatingRightAtRightBorder(){
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,1);
            game.dropOneLineDown();
            testRotatingTetromino(game,1,3);
            testRotatingTetromino(game,1,2);
            
        }
    }
    /**
     * testaa, että pyöritys vasemmalle toimii oikean seinän vieressä
     * samalla wallKick testaantuu
     */
    @Test
    public void TestRotatingLeftAtRightBorder(){
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,1);
            game.dropOneLineDown();
            testRotatingTetromino(game,-1,1);
            testRotatingTetromino(game,-1,2);
            
        }
    }
        /**
     * testaa, että pyöritys oikealle toimii vasemman seinän vieressä
     * 
     */
    @Test
    public void TestRotatingRightAtLeftBorder(){
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,-1);
            game.dropOneLineDown();
            testRotatingTetromino(game,1,3);
            testRotatingTetromino(game,1,2);
            
        }
    }
    /**
     * testaa, että pyöritys vasemmalle toimii vasemman seinän vieressä
     * 
     */
    @Test
    public void TestRotatingLeftAtLeftBorder(){
        for (int j = 0; j < 100; j++) {
            GameLogic game = new GameLogic();
            game.setNewFallingTetromino();
            game.setNewFallingTetromino();
            moveTetrominoToBorder(game,-1);
            game.dropOneLineDown();
            testRotatingTetromino(game,-1,1);
            testRotatingTetromino(game,-1,2);
            
        }
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
        /*
     *palauttaa halutun rivin halutusta vastaustaulukosta
     */
    private int[][] getCordinatesOfRow(int rowNumber, int[][][] table) {
        int[][] row = new int[4][2];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(table[rowNumber][i], 0, row[i], 0, 2);
        }
        return row;
    }
    /**
     * apumetodi, joka siirtää tetromiinon reunaan
     * @param game
     * @param direction 
     */
    private void moveTetrominoToBorder(GameLogic game, int direction){
        for (int i = 0; i < 5; i++) {//tetromino liikutettu laitaan
                game.setIsMoved(direction);
                game.moveTetromino();
            }
    }
    /**
     * apumetodi, joka poistaa copypastea movetetromino testeistä 
     * @param game
     * @param direction on 1 tai -1 riippuen liikuttamissuunnast
     * @param expectedX on oletettu X kordinaatti
     */
    private void testMoveTetrominoAtBoarder(GameLogic game,int direction, int expectedX){
        assertEquals(expectedX,game.getGlobalX());
        game.setIsMoved(direction);
        game.moveTetromino();
        assertEquals(game.getGlobalX(),expectedX);
    }
    /**
     * apumetodi, joka poistaa copy pastea rotateTetromino testeistä
     * @param game
     * @param direction on 1 tai -1 riippuen rotaatiosuunnasta
     * @param usedCheckTable kertoo mitä tarkistustaulukkoa kuuluu käyttää
     */
    private void testRotatingTetromino(GameLogic game, int direction, int usedCheckTable){
            game.setIsRotated(direction);
            game.rotateTetromino();
            int [][] tetromino=getCordinateTableOfTetromino(game.getFallingTetromino());
            if(usedCheckTable==1){
                Assert.assertArrayEquals(tetromino,getCordinatesOfRow(game.getFallingTetromino().getShape().ordinal(),this.cordinateTable1Left));
            }
            else if(usedCheckTable==2){
                Assert.assertArrayEquals(tetromino,getCordinatesOfRow(game.getFallingTetromino().getShape().ordinal(),this.cordinateTable2Left));
            }
            else{
                Assert.assertArrayEquals(tetromino,getCordinatesOfRow(game.getFallingTetromino().getShape().ordinal(),this.cordinateTable3Left));
            }
            
    }
}
