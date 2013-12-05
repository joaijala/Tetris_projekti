/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.HighScore;

import userInterface.highscore.Score;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa score luokkaa. 
 * @author josse
 */
public class ScoreTest {
    
    public ScoreTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Testaa, että konstruktori toimii oikein
     */
    
    @Test
    public void testMakeNewScore(){
        Score score;
        score = new Score(100,"josse");
        assertEquals(score.getName(),"josse");
        assertEquals(score.getScore(),100);
        
    }

    @Test
    public void testCompareTo() {
        Score score1 = new Score(100,"josse");
        Score score2 = new Score(200,"josse");
        Score score3 = new Score(100,"josse");
        assertEquals(score1.compareTo(score3),0);
        assertEquals(score1.compareTo(score2),1);
        assertEquals(score2.compareTo(score1),-1);
    }

    

    
    
}
