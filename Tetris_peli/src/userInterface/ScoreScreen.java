/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;

/**
 *
 * @author Johanna
 */
public class ScoreScreen extends JPanel{
    
    private final GameLogic game;
    
    
    public ScoreScreen(GameLogic game){
        this.game=game;
        
    }
    
    @Override
    public void paint(Graphics graphics){
        int score=this.game.getScore();
        
        graphics.setColor(new Color(0,0,0));
        graphics.fillRoundRect(254, 184, 122, 52, 25, 25);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(255, 185, 120, 50, 25, 25);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(new Font("Verdet",0,25));
        if(score<10){
           graphics.drawString(Integer.toString(score),305,225); 
        }
        else if(score<100){
           graphics.drawString(Integer.toString(score),300,225); 
        }
        else if(score<1000){
           graphics.drawString(Integer.toString(score),290,225); 
        }
        else if(score<10000){
            graphics.drawString(Integer.toString(score),285,225);
        }
        else if(score<100000){
           graphics.drawString(Integer.toString(score),280,225); 
        }
        else {
           graphics.drawString(Integer.toString(score),270,225); 
        }
        
        graphics.setFont(new Font ("Arial",1,18));
        graphics.drawString("Score", 290, 200 );
        
        
    }
    
}
