/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.HighScore;

import userInterface.highscore.Score;
import userInterface.highscore.HighScoreManager;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johanna
 */
public class HighScoreManagerTest {
    
    public HighScoreManagerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    //Testaa, että jkonstruktori toimii oikein
    @Test 
    public void testMakeNewScoreManager(){
        HighScoreManager manager=new HighScoreManager();
        assertFalse(null==manager.getScores());
    }
    //Testaa, että add score toimii oikein
    @Test
    public void testAddScore(){
        HighScoreManager manager=new HighScoreManager();
        Score score=new Score(0,null);
        if(manager.getScores().size()==10){
            score=manager.getScore(9);
        }
        manager.addScore("test", 100001);
        assertEquals(manager.getScore(0).getName(),"test");
        assertEquals(manager.getScore(0).getScore(),100001);
        manager.removeScore("test");
        if(manager.getScores().size()==10){
            manager.addScore(score.getName(), score.getScore());
        }
    
    }
    //Testaa, että remove score toimii oikein
    @Test
    public void testRemoveScore(){
        HighScoreManager manager=new HighScoreManager();
        Score score=new Score(0,null);
        if(manager.getScores().size()==10){
            score=manager.getScore(9);
        }
        ArrayList<Score> scores=manager.getScores();
        manager.addScore("test", 100000);
        manager.removeScore("test");
        if(manager.getScores().size()==10){
            manager.addScore(score.getName(), score.getScore());
        }
        assertEquals(scores,manager.getScores());
    }
   /* Testi poissa käytöstä, sillä se hävittää higScoret
    //Testaa, että clear cores tyhjentää koko scoren
    @Test
    public void testClearScores(){
        HighScoreManager manager=new HighScoreManager();
        ArrayList<Score> scores=manager.getScores();
        manager.clearScores();
        assertEquals(manager.getScores().size(),0);
        for(int i=0;i<scores.size();i++){
            manager.addScore(scores.get(i).getName(), scores.get(i).getScore());
        }
    }*/
    
}
