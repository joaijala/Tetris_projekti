/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 *
 * @author Johanna
 */
public class GameBackgroundScreen extends JPanel {
   
    
    public GameBackgroundScreen(){
        
    }
    
    @Override
    public void paint(Graphics graphics){
        
        double height =500;
        double width =500;
        for(int x=0;x<(width/20);x++){
            for(int y=0; y<(height/20);y++){
                drawSquare(graphics,x*20,y*20);
            }
        }
    }
    
    private void drawSquare(Graphics graphics, int x, int y) {
        Color color = new Color(150, 150, 125);
        graphics.setColor(color);
        graphics.fillRect(x + 1, y , 20 - 1, 20 - 1);
        
        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x+19, y);
        //graphics.drawLine(x+1, y+1, x+18, y+1);
        graphics.drawLine(x+19, y, x+19, y+19);
        //graphics.drawLine(x+18, y+1, x+18, y+18);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        //graphics.drawLine(x + 2, y + 18, x + 18, y + 18);
        graphics.drawLine(x, y + 19, x, y + 1);
        //graphics.drawLine(x + 1, y + 18, x + 1, y + 2);
        
       

    }
}
