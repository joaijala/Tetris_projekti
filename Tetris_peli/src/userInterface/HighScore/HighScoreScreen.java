/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.HighScore;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import userInterface.MenuScreen;

/**
 *
 * @author Johanna
 */
public class HighScoreScreen extends JPanel{
    HighScoreManager highScore;
    MenuScreen menuScreen;
    public HighScoreScreen(HighScoreManager manager,MenuScreen screen){
        highScore=manager;
        menuScreen=screen;
        
    }
    
    @Override
    public void paintComponent(Graphics graphics){
        paintBackground(graphics);
    }
    
    public void paintBackground(Graphics graphics){
        double height =480;
        double width =430;
        for(int x=0;x<(width/20);x++){
            for(int y=0; y<(height/20);y++){
                drawSquare(graphics,x*20,y*20);
            }
        }
        graphics.setColor(Color.black);
        graphics.fillRect(15, 15, 210, 410);
        graphics.setColor(Color.white);
        graphics.fillRect(20, 20, 200, 400);
        
    }
    private void drawSquare(Graphics graphics, int x, int y) {
        Color color=new Color(149, 218, 225);
        graphics.setColor(color);
        graphics.fillRect(x + 1, y , 20 - 1, 20 - 1);
        
        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x+19, y);
        graphics.drawLine(x+19, y, x+19, y+19);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x, y + 19, x, y + 1);
        
       

    }
    
}
