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
import javax.swing.JComponent;
import tetrisGame.ControllListener;

/**
 *
 * @author Johanna
 */
public class UserInterface extends JFrame implements Runnable{
    
    private GameScreen gameScreen;
    private GameLogic game;
    public Container container;
    private MenuScreen menuScreen;
    public boolean isGameOn=false;
    
    
            
    
    public UserInterface(){
         setTitle("Tetris");
         setPreferredSize(new Dimension(430,480));
         setResizable(false);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         createComponent();
         
         setContentPane(menuScreen);
         pack();
         setVisible(true);
         setFocusable(true);
         
         
    }
    @Override
    public void run(){
        while(true){
            if(isGameOn){
                game.gameLoop();
                isGameOn=false;
                setContentPane(menuScreen);
                setVisible(true);
            }
            delay(10);
        }
    }
    private void delay(int delay){
        double time=getCurrentTimeInMilliseconds();
        while(getCurrentTimeInMilliseconds()<time+delay){
            //wait;
        }
    }
    private double getCurrentTimeInMilliseconds() {
        return System.nanoTime() * 0.000001;
    }

    
    public void createComponent(){
        game=new GameLogic();
        this.gameScreen=game.getGameScreen();
        menuScreen=new MenuScreen(this);
        
    }
    public JFrame getFrame(){
        return this;
    }
    
    
    public void startGame(){
        
        game=new GameLogic();
        this.gameScreen=game.getGameScreen();
        setContentPane(gameScreen);
        setVisible(true);
        
        ControllListener listener=new ControllListener(gameScreen.game);
        addKeyListener(listener);
        this.isGameOn=true;
    }
}
