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
import userInterface.HighScore.HighScoreManager;
import userInterface.HighScore.HighScoreScreen;
import userInterface.HighScore.newHighScoreScreen;

/**
 * Käyttöliittymän perusta, joka hoitaa ohjelman pyörittämisen
 * @author Johanna
 */
public class UserInterface extends JFrame implements Runnable{
    
    private GameScreen gameScreen;
    private GameLogic game;
    public Container container;
    private MenuScreen menuScreen;
    private boolean GameOn=false;
    private boolean showHighscore=false;
    private HighScoreManager highScores;
    private HighScoreScreen highScoreScreen;
    private int score;
    private boolean newHighScore;
    private newHighScoreScreen newScoreScreen;
    
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
        while(true){
            if(GameOn){
                startGame();
                
                setContentPane(menuScreen);
                setVisible(true);
            }
            if(newHighScore){
                this.newScoreScreen=new newHighScoreScreen(this.score,this.highScores,this);
                setContentPane(newScoreScreen);
                setVisible(true);
                while(newHighScore){
                    delay(10);
                }
            }
            if(showHighscore){
                setContentPane(highScoreScreen);
                setVisible(true);
                while(showHighscore){
                    delay(10);
                }
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
        highScores=new HighScoreManager();
        highScoreScreen=new HighScoreScreen(highScores,this);
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
        ControllListener listener=new ControllListener(gameScreen.game);
        addKeyListener(listener);
        setContentPane(gameScreen);
        setVisible(true);
        GameOn=false;
        this.score=game.gameLoop();
        checkForHighScore();
    }
    /*
     * asettaa GameOn trueks jotta runin ohjelmalooppi voi käynnistää pelin
     */
    public void setGameOn(){
        this.GameOn=true;
    }
    public void setShowHighscore(boolean status){
        this.showHighscore=status;
    }
    public void checkForHighScore(){
        if(this.score==0){
            setContentPane(menuScreen);
            setVisible(true);
            return;
        }
        if (this.score>this.highScores.getSmallestScore()){
            this.newHighScore=true;
        }
        this.showHighscore=true;
        
    }
    public void setNewHighScore(boolean status){
        this.newHighScore=status;
    }
    
}