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
import userInterface.GameScreen.GameScreen;
import userInterface.HighScore.HighScoreManager;
import userInterface.UserInterface;
public class Main {
  
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        UserInterface UI =new UserInterface();
        UI.run();/*
        HighScoreManager hm = new HighScoreManager();
        hm.addScore("Bart",240);
        hm.addScore("Marge",300);
        hm.addScore("Maggie",220);
        hm.addScore("Homer",100);
        hm.addScore("Lisa",270);

       System.out.print(hm.getHighscoreString());*/

             
    }
        
        
        

}
    

