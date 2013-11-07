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

    
}