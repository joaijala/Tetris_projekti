/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.GameScreen;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;
import tetrisGame.Tetromino;

/**
 * Tämä luokka hoitaa tippuvan tetrominon piirtämisen pelilaaudalle
 * @author Josse
 */
public class FallingTetrominoScreen extends JPanel{

    private final GameLogic game;
    /**
     * Sisältää tiedon eri tetrominojen väristä.
     */
    private final Color colors[] = {new Color(255, 255, 255), new Color(240, 0, 0),
                          new Color(0, 240,0), new Color(0, 240, 240),
                          new Color(160, 0, 240), new Color(240, 240, 0),
                          new Color(240, 160, 0), new Color(0, 0, 240)
        };

    /**
     * 
     * @param game
     */
    public FallingTetrominoScreen(GameLogic game) {
        this.game=game;

        
    }
    /**
     * paint piirtää ensin pelilaudan ja sen jälkeen pelilaudan päälle tetrominon 
     * @param graphics 
     */
    
    @Override
    public void paintComponents(Graphics graphics) {
        int squareSize=20;
        Tetromino tetromino = game.getFallingTetromino();
        if(game.getIsTetrominoFalling()){
            for (int i = 0; i < 4; ++i) {
            int x = game.getGlobalX() + tetromino.getX(i);
            int y = game.getGlobalY() + tetromino.getY(i);
            drawSquare(graphics,20+x * squareSize,
                     20+y * squareSize,
                    tetromino.getShape().ordinal());

        }
        }
        
    }
    /**
     * Piirtää yhden tetromino ruudun sille annetulle kohdalle
     *
     * @param graphics
     * @param x ruudun ylävasemman kulman x kordinaatti.
     * @param y ruudun ylävasemman kulman y kordinaatti.
     * @param shape minkä tetrominon shape ruudussa on.
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

