/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;
import tetrisGame.Tetromino;

/**
 *
 * @author Johanna
 */
public class NextTetrominoScreen extends JPanel {

    private final Color colors[] = {new Color(255, 255, 255), new Color(240, 0, 0),
                                    new Color(0, 240, 0), new Color(0, 240, 240),
                                    new Color(160, 0, 240), new Color(240, 240, 0),
                                    new Color(240, 160, 0), new Color(0, 0, 240)
    };
    private GameLogic game;

    public NextTetrominoScreen(GameLogic game) {
        this.game = game;
    }
    /**
     * piirtää ruudun jossa näkyy seuraava tetromiino keskellä ruutua
     * @param graphics 
     */
    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(new Color(0,0,0));
        graphics.fillRoundRect(254, 39, 122, 122, 25, 25);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(255, 40, 120, 120, 25, 25);

        Tetromino tetromino = game.getNextTetromino();

        for (int i = 0; i < 4; ++i) {
            int x = tetromino.getX(i);
            int y = tetromino.getY(i);
            if (tetromino.getShape().ordinal() == 4||tetromino.getShape().ordinal() == 3) {
                drawSquare(graphics, 305 + x * 20,
                        80 + y * 20,
                        tetromino.getShape().ordinal());
            }
            else if(tetromino.getShape().ordinal() == 5){
               drawSquare(graphics, 295 + x * 20,
                        80+ y * 20,
                        tetromino.getShape().ordinal());
            
            }
            else {
                drawSquare(graphics, 295 + x * 20,
                        90 + y * 20,
                        tetromino.getShape().ordinal());
            }
            graphics.setFont(new Font ("Arial",1,15));
            graphics.drawString("Next Tetromino", 262, 58 );
            
        }
    }

    private void drawSquare(Graphics graphics, int x, int y, int shape) {
        Color color = colors[shape];

        graphics.setColor(color);
        if (shape == 0) {
            graphics.fillRect(x, y, 20, 20);
            return;
        }
        graphics.fillRect(x + 2, y + 2, 20 - 4, 20 - 4);

        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x + 19, y);
        graphics.drawLine(x + 1, y + 1, x + 18, y + 1);
        graphics.drawLine(x + 19, y, x + 19, y + 19);
        graphics.drawLine(x + 18, y + 1, x + 18, y + 18);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x + 2, y + 18, x + 18, y + 18);
        graphics.drawLine(x, y + 19, x, y + 1);
        graphics.drawLine(x + 1, y + 18, x + 1, y + 2);

    }
}
