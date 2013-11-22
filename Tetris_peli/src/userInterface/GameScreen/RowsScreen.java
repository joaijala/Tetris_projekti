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
 * tämä luokka piirtää pelikenttään ruudun, jossa näkyy tyhjennettyjen rivien määrä
 * @author Johanna
 */
public class RowsScreen extends JPanel {
   
    
    private final GameLogic game;
    
    
    public RowsScreen(GameLogic game){
        this.game=game;
        
    }
    
    @Override
    public void paintComponents(Graphics graphics){
        int rows=this.game.getClearedRows();
        graphics.setColor(new Color(0,0,0));
        graphics.fillRoundRect(254, 334, 122, 52, 25, 25);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(255, 335, 120, 50, 25, 25);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(new Font("Verdet",0,25));
        if(rows<10){
           graphics.drawString(Integer.toString(rows),305,375); 
        }
        else if(rows<100){
            graphics.drawString(Integer.toString(rows),300,375);
        }
        else{
            graphics.drawString(Integer.toString(rows),290,375);
        }
        graphics.setFont(new Font ("Arial",1,18));
        graphics.drawString("Rows", 290, 350);
        
        
    }
}
