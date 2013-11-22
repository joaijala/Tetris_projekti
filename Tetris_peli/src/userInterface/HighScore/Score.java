/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.HighScore;

import java.io.Serializable;

/**
 *
 * @author Johanna
 */
public class Score implements  Serializable,Comparable<Score>{ 
    
    private final int score;
    private final String name;
    
    public Score(int score,String name){
        this.score=score;
        this.name=name;
    }
    
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Score score1) {              
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
    
}
