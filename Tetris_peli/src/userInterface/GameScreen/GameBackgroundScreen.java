/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.GameScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;


/**
 * Tämä luokka hoitaa peliruudun taustan piirtämisen.
 * @author Johanna
 */
public class GameBackgroundScreen extends JPanel {
        GameLogic game;
        private final Color colors[] = {new Color(149, 218, 225),new Color(124, 255,121),
                          new Color(194, 149,255),new Color(119, 170,255),
                          new Color(149, 213,103),new Color(220, 219,111),
                          new Color(255, 193,95),new Color(255, 143,108),
                          new Color(0, 156,255),new Color(255, 240,71),
                          new Color(49, 255,255),new Color(255, 37,37),
                          
        };
    
    public GameBackgroundScreen(GameLogic game){
        this.game=game;
        
    }
    
    @Override
    public void paintComponents(Graphics graphics){
        
        double height =480;
        double width =430;
        for(int x=0;x<(width/20);x++){
            for(int y=0; y<(height/20);y++){
                drawSquare(graphics,x*20,y*20);
            }
        }
        
    }
    
    private void drawSquare(Graphics graphics, int x, int y) {
        
        graphics.setColor(colors[game.getLevel()]);
        graphics.fillRect(x + 1, y , 20 - 1, 20 - 1);
        
        graphics.setColor(colors[game.getLevel()].brighter());
        graphics.drawLine(x, y, x+19, y);
        //graphics.drawLine(x+1, y+1, x+18, y+1);
        graphics.drawLine(x+19, y, x+19, y+19);
        //graphics.drawLine(x+18, y+1, x+18, y+18);

        graphics.setColor(colors[game.getLevel()].darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        //graphics.drawLine(x + 2, y + 18, x + 18, y + 18);
        graphics.drawLine(x, y + 19, x, y + 1);
        //graphics.drawLine(x + 1, y + 18, x + 1, y + 2);
        
       

    }
}
