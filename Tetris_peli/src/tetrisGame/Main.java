/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

/**
 *
 * @author Johanna
 */
import tetrisGame.Tetromino.Shape;
public class Main {
  
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //testausta
        Tetromino tetrispalikka= new Tetromino(Shape.LShape);
        Tetromino tetrispalikka2= new Tetromino(Shape.LShape);
        tetrispalikka2.rotateLeft();
        tetrispalikka2.rotateRight();
        Tetromino tetrispalikka3= new Tetromino(Shape.LShape);        
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka.getX(i)+", "+ tetrispalikka.getY(i));
         }
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka2.getX(i)+", "+ tetrispalikka2.getY(i));
         }
         for (int i = 0; i < 4 ; i++) {
            System.out.println(tetrispalikka3.getX(i)+", "+ tetrispalikka3.getY(i));
         }
             
    }
        
        
        
}
    

