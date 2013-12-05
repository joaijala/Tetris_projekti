/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.gamescreen;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;


/**
 * Tämä luokka hoitaa peliruudun taustan piirtämisen.
 * @author Josse
 */
public class GameBackgroundScreen extends JPanel {
        GameLogic game;
        /**
         * Sisältää tiedon eri pelin leveleiden taustan väreistä.
         */
        private final Color colors[] = {new Color(149, 218, 225),new Color(124, 255,121),
                          new Color(194, 149,255),new Color(119, 170,255),
                          new Color(149, 213,103),new Color(220, 219,111),
                          new Color(255, 193,95),new Color(255, 143,108),
                          new Color(0, 156,255),new Color(255, 240,71),
                          new Color(49, 255,255),new Color(255, 37,37),
                          
        };
    
    /**
     *
     * @param game
     */
    public GameBackgroundScreen(GameLogic game){
        this.game=game;
        
    }
    
    /**
     * Piitää pelilaudan taustan.
     * @param graphics
     */
    @Override
    public void paintComponents(Graphics graphics){
        
        double screenHeight =480;
        double screenWidth =430;
        int squareSize=20;
        for(int x=0;x<(screenWidth/squareSize);x++){
            for(int y=0; y<(screenHeight/squareSize);y++){
                drawSquare(graphics,x*squareSize,y*squareSize);
            }
        }
        
    }
    /**
     * Piirtää yhden pelilaudan taustan ruuduista.
     *
     * @param graphics
     * @param x ruudun ylä vasemman kulman x kordinaatti
     * @param y ruudun ylä vasemman kulman y kordinaatto
     */
    private void drawSquare(Graphics graphics, int x, int y) {
        
        graphics.setColor(colors[game.getLevel()]);
        graphics.fillRect(x + 1, y , 19, 19);
        
        graphics.setColor(colors[game.getLevel()].brighter());
        graphics.drawLine(x, y, x+19, y);
        graphics.drawLine(x+19, y, x+19, y+19);

        graphics.setColor(colors[game.getLevel()].darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x, y + 19, x, y + 1);
    }
}
