/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris_game;

/**
 *
 * @author Johanna
 */
import tetris_game.Tetromino.Shape;
public class Main {
  
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //testausta
        Tetromino tetrispalikka= new Tetromino(Shape.L_shape);
        Tetromino tetrispalikka2= new Tetromino(Shape.L_shape);
        tetrispalikka2.rotate_left();
        tetrispalikka2.rotate_right();
        Tetromino tetrispalikka3= new Tetromino(Shape.L_shape);        
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka.get_X(i)+", "+ tetrispalikka.get_Y(i));
         }
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka2.get_X(i)+", "+ tetrispalikka2.get_Y(i));
         }
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka3.get_X(i)+", "+ tetrispalikka3.get_Y(i));
         }
             
    }
        
        
        
}
    

