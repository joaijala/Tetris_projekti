/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tetrisGame.Tetromino;
import tetrisGame.Board;
import tetrisGame.GameLogic;
import tetrisGame.Tetromino.Shape;
import tetrisGame.ControllListener;

/**
 *
 * @author Johanna
 */
public class GameScreen extends JPanel {
    
    
    public GameLogic game;
    private final Color colors[] = {new Color(255, 255, 255), new Color(240, 0, 0),
                          new Color(0, 240,0), new Color(0, 240, 240),
                          new Color(160, 0, 240), new Color(240, 240, 0),
                          new Color(240, 160, 0), new Color(0, 0, 240)
        };
    public ControllListener listener;

    public GameScreen() {
        this.game=new GameLogic(this);

        
    }
    /**
     * paint piirtää ensin pelilaudan ja sen jälkeen pelilaudan päälle tetrominon 
     * @param graphics 
     */
    
    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(new Color(0,0,0));
        graphics.fillRect(0, 0, 210, 410);
        
        Tetromino tetromino = game.getTetromino();
        int[][] boardStatus = game.getBoard().getBoardStatus();
        Dimension size = getSize();
        int boardTop=5;

        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 10; ++j) {
                int shape = boardStatus[i][j];

                drawSquare(graphics,5+ j * 20,
                      5+ i * 20, shape);

            }
        }

        for (int i = 0; i < 4; ++i) {
            int x = game.getGlobalX() + tetromino.getX(i);
            int y = game.getGlobalY() + tetromino.getY(i);
            drawSquare(graphics,5+x * 20,
                     5+y * 20,
                    tetromino.getShape().ordinal());

        }
    }
    /**
     * Piirtää yhsen tetromino ruudun sille annetulle kohdalle
     * @param graphics
     * @param x
     * @param y
     * @param shape 
     */
    private void drawSquare(Graphics graphics, int x, int y, int shape) {
        Color color = colors[shape];
      
        graphics.setColor(color);
        if(shape==0){
           graphics.fillRect(x, y ,20 , 20); 
           return;
        }
        graphics.fillRect(x + 2, y + 2,20 - 4, 20 - 4);

        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x+19, y);
        graphics.drawLine(x+1, y+1, x+18, y+1);
        graphics.drawLine(x+19, y, x+19, y+19);
        graphics.drawLine(x+18, y+1, x+18, y+18);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y +19,x + 19, y + 19);
        graphics.drawLine(x+2, y+18, x+18, y+18);
        graphics.drawLine(x, y + 19,x, y + 1);
        graphics.drawLine(x+1, y+18, x+1, y+2);

    }

}
