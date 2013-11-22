/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.GameScreen;

import java.awt.Graphics;

import javax.swing.JPanel;
import tetrisGame.ControllListener;

import tetrisGame.GameLogic;

        

/**
 * GameScreen pitää sisällään kaikki peliruudun osat, jotka kaikki piirtyy kun gameScreenin repainttia kutsutaan
 * @author Johanna
 */
public class GameScreen extends JPanel {
    
    
    public GameLogic game;
    private final BoardScreen boardScreen;
    private final NextTetrominoScreen nextTetrominoScreen;
    private final GameBackgroundScreen backgroundScreene;
    private final ScoreScreen pointScreen;
    private final LevelScreen levelScreen;
    private final RowsScreen rowsScreen;
    private final FallingTetrominoScreen fallingTetromino;
    PauseScreen pauseScreen;
    
    
    public ControllListener listener;

    public GameScreen(GameLogic game) {
        this.game=game;
        this.boardScreen=new BoardScreen(game);
        this.nextTetrominoScreen=new NextTetrominoScreen(game);
        this.backgroundScreene=new GameBackgroundScreen(game);
        this.pointScreen=new ScoreScreen(game);
        this.levelScreen= new LevelScreen(game);
        this.rowsScreen= new RowsScreen(game);
        this.fallingTetromino=new FallingTetrominoScreen(game);
        this.pauseScreen=new PauseScreen(game);
        
        
    }
    @Override
    public void paint(Graphics graphics){
        this.backgroundScreene.paintComponents(graphics);
        this.boardScreen.paint(graphics);
        this.nextTetrominoScreen.paint(graphics);
        this.pointScreen.paint(graphics);
        this.levelScreen.paint(graphics);
        this.rowsScreen.paint(graphics);
        this.fallingTetromino.paint(graphics);
        if(this.game.getIsPaused()){
            this.pauseScreen=new PauseScreen(game);
            this.pauseScreen.paintComponents(graphics);
            
        }
        
    }
    
    
    
    

}
