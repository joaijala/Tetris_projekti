/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import userInterface.GameScreen.GameScreen;
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
    private boolean GameOn=false;
    private boolean isProgramOn=true;
    
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
    /**
     * tässä on toteutettu ohjelmalooppi
     */
    @Override
    public void run(){
        while(isProgramOn){
            if(GameOn){
                startGame();
                GameOn=false;
                setContentPane(menuScreen);
                setVisible(true);
            }
            delay(10);
        }
    }
    /**
     * laittaa kutsujathreadin odottamaan looppiin kunnes aika on kulunut
     * @param delay -aika miten kauan pitäisi odottaa
     */
    private void delay(int delay){
        double time=getCurrentTimeInMilliseconds();
        while(getCurrentTimeInMilliseconds()<time+delay){
            //wait;
        }
    }
    /**
     * 
     * @return javan virtuaalikoneen tämänhetkinen aika millisekunneissa
     */
    private double getCurrentTimeInMilliseconds() {
        return System.nanoTime() * 0.000001;
    }
    /**
     * luo kaikki komponentit, mitkä pitäisi olla kun ohjelma käynistyy
     */
    
    public void createComponent(){
        menuScreen=new MenuScreen(this);
        
    }
    /**
     * 
     * @return palautta userInterface JFramen
     */
    public JFrame getFrame(){
        return this;
    }
    
    /**
     * luo uuden pelin, laittaa sen näkyville ruutuun ja käynnistää peliloopin
     */
    public void startGame(){
        
        game=new GameLogic();
        this.gameScreen=game.getGameScreen();
        setContentPane(gameScreen);
        setVisible(true);
        ControllListener listener=new ControllListener(gameScreen.game);
        addKeyListener(listener);
        game.gameLoop();
    }
    /*
     * asettaa GameOn trueks jotta runin ohjelmalooppi voi käynnistää pelin
     */
    public void setGameOn(){
        this.GameOn=true;
    }
    
}