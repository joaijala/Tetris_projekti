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
    private BoardScreen boardScreen;
    private NextTetrominoScreen nextTetrominoScreen;
    private GameBackgroundScreen backgroundScreene;
    private ScoreScreen pointScreen;
    private LevelScreen levelScreen;
    private RowsScreen rowsScreen;
    private final Color colors[] = {new Color(255, 255, 255), new Color(240, 0, 0),
                          new Color(0, 240,0), new Color(0, 240, 240),
                          new Color(160, 0, 240), new Color(240, 240, 0),
                          new Color(240, 160, 0), new Color(0, 0, 240)
        };
    public ControllListener listener;

    public GameScreen(GameLogic game) {
        this.game=game;
        this.boardScreen=new BoardScreen(game);
        this.nextTetrominoScreen=new NextTetrominoScreen(game);
        this.backgroundScreene=new GameBackgroundScreen();
        this.pointScreen=new ScoreScreen(game);
        this.levelScreen= new LevelScreen(game);
        this.rowsScreen= new RowsScreen(game);
        
    }
    @Override
    public void paint(Graphics graphics){
        this.backgroundScreene.paint(graphics);
        this.boardScreen.paint(graphics);
        this.nextTetrominoScreen.paint(graphics);
        this.pointScreen.paint(graphics);
        this.levelScreen.paint(graphics);
        this.rowsScreen.paint(graphics);
    }


}
