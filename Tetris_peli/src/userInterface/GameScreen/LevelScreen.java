/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.GameScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;

/**
 *
 * @author Johanna
 */
public class LevelScreen extends JPanel{
    
    
    private final GameLogic game;
    
    
    public LevelScreen(GameLogic game){
        this.game=game;
        
    }
    
    @Override
    public void paint(Graphics graphics){
        int level=this.game.getLevel();
        
        graphics.setColor(new Color(0,0,0));
        graphics.fillRoundRect(264, 259, 102, 52, 25, 25);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(265, 260, 100, 50, 25, 25);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(new Font("Verdet",0,25));
        graphics.drawString(Integer.toString(level),305,300);
        graphics.setFont(new Font ("Arial",1,18));
        graphics.drawString("Level", 290, 275 );
        
        
    }
}
