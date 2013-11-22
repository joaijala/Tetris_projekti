/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface.GameScreen;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import tetrisGame.GameLogic;

/**
 *
 * @author Johanna
 */
public class PauseScreen extends JPanel {
    GameLogic game;
    Button resume;
    private GridBagConstraints container;
    
    public PauseScreen(GameLogic game){
        this.game=game;
        makeButton();
    }
    
    @Override
    public void paintComponents(Graphics graphics){
        this.setVisible(game.getIsPaused());
        Color color=new Color(100,100,100,200);
        graphics.setColor(color);
        graphics.fillRect(0, 0, 430, 480);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font ("Arial",0,40));
        graphics.drawString("Game Paused", 100, 50);
        
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
