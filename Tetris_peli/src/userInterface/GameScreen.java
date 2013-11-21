/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import tetrisGame.GameLogic;
import tetrisGame.ControllListener;

/**
 *
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
        
        
    }
    @Override
    public void paint(Graphics graphics){
        this.backgroundScreene.paint(graphics);
        this.boardScreen.paint(graphics);
        this.nextTetrominoScreen.paint(graphics);
        this.pointScreen.paint(graphics);
        this.levelScreen.paint(graphics);
        this.rowsScreen.paint(graphics);
        this.fallingTetromino.paint(graphics);
        
    }
    
    
    
    

}
