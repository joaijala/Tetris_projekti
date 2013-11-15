/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;
import tetrisGame.GameLogic;

/**
 *
 * @author Johanna
 */
public class ScoreScreen extends JPanel{
    
    private GameLogic game;
    private JLabel label;
    
    public ScoreScreen(GameLogic game){
        this.game=game;
        this.label=new JLabel();
        
    }
    
    @Override
    public void paint(Graphics graphics){
        int score=this.game.getScore();
        
        graphics.setColor(new Color(0,0,0));
        graphics.fillRoundRect(249, 169, 122, 42, 25, 25);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(250, 170, 120, 40, 25, 25);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(new Font("Verdet",0,25));
        graphics.drawString(Integer.toString(score),260,200);
        
        
        
    }
    
}
