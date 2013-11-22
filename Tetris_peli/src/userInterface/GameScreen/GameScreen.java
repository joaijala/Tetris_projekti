/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.GameScreen;

import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import tetrisGame.ControllListener;

import tetrisGame.GameLogic;

        

/**
 * GameScreen pitää sisällään kaikki peliruudun osat, jotka kaikki piirtyy kun gameScreenin repainttia kutsutaan
 * @author Johanna
 */
public class GameScreen extends JPanel {
    
   Button resume;
    private GridBagConstraints container; 
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
        makeButton();
        
        
        
    }
    @Override
    public void paint(Graphics graphics){
        resume.setVisible(false);
        this.backgroundScreene.paintComponents(graphics);
        this.boardScreen.paintComponents(graphics);
        this.nextTetrominoScreen.paintComponents(graphics);
        this.pointScreen.paintComponents(graphics);
        this.levelScreen.paintComponents(graphics);
        this.rowsScreen.paintComponents(graphics);
        this.fallingTetromino.paintComponents(graphics);
        if(this.game.getIsPaused()){
            resume.setVisible(true);
            this.pauseScreen.paintComponents(graphics);
            
        }
        
    }
    public void makeButton(){
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();
        resume=new Button("Resume");
        resume.setFont(new Font("Arial", 0, 30));
        resume.setBounds(100, 100, 100, 20);
        resume.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                game.setIsPaused();

            }
        });
        add(resume, container);
        
        
    }
    
    
    
    

}
