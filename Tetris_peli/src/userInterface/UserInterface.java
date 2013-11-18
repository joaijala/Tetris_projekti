/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetrisGame.GameLogic;
import java.awt.Container;
import java.awt.Font;
import tetrisGame.ControllListener;

/**
 *
 * @author Johanna
 */
public class UserInterface implements Runnable{
    private JFrame frame;
    private GameScreen gameScreen;
    
            
    
    public UserInterface(){

    }

    @Override
    public void run() {
         frame =new JFrame("Tetris");
         frame.setPreferredSize(new Dimension(430,480));
         frame.setResizable(false);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         createComponent(frame.getContentPane());
         
         frame.pack();
         frame.setVisible(true);
         frame.setFocusable(true);
         ControllListener listener=new ControllListener(gameScreen.game);
         frame.addKeyListener(listener);
        
         
    }
    public void createComponent(Container container){
        GameLogic game=new GameLogic();
        this.gameScreen=game.getGameScreen();
        container.add(gameScreen);
    }
    public JFrame getFrame(){
        return frame;
    }
    public void startGame(){
        gameScreen.game.gameLoop();
    }
}
