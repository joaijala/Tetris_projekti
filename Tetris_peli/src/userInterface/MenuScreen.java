/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.LINE_END;
import static java.awt.GridBagConstraints.LINE_START;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.PAGE_END;
import static java.awt.GridBagConstraints.PAGE_START;
import static java.awt.GridBagConstraints.REMAINDER;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * Ruutu, joka näkyy kun ollaa mainMenu screenissa
 *
 * @author Johanna
 */
public class MenuScreen extends JPanel {

    private UserInterface frame;
    private Button startButton;
    private Button quitButton;
    private Button highScore;
    private GridBagConstraints container;

    public MenuScreen(UserInterface frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();
        this.makeButtons();

    }

    private void makeButtons() {
        makeStartButton();

        makeHighscoreButton();
        
        makeQuitButton();

    }

    private void makeStartButton() {
        startButton = new Button("Start Game");
        startButton.setFont(new Font("Arial", 0, 30));
        container.insets = new Insets(10, 40, 10, 10);
        container.gridy = 0;
        container.weightx=5;
        container.anchor = LINE_START;
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setGameOn();

            }
        });
        add(startButton, container);
    }

    private void makeHighscoreButton() {
        highScore = new Button("High Score");
        highScore.setFont(new Font("Arial", 0, 30));
        container.gridy = 1;
        highScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setShowHighscore(true);
            }
        });
        add(highScore, container);
    }

    private void makeQuitButton() {
        quitButton = new Button("Quit Game");
        quitButton.setFont(new Font("Arial", 0, 30));

        container.gridy = 2;
        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                System.exit(0);
            }
        });

        add(quitButton, container);
    }
    @Override
    public void paintComponent(Graphics graphics){
        paintBackground(graphics);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial",0,35));
        graphics.drawString("Main Menu", 40, 60);
    }
    
    public void paintBackground(Graphics graphics){
        double height =480;
        double width =430;
        for(int x=0;x<(width/20);x++){
            for(int y=0; y<(height/20);y++){
                drawSquare(graphics,x*20,y*20);
            }
        }
        graphics.setColor(Color.black);
        graphics.fillRect(15, 15, 210, 410);
        graphics.setColor(Color.white);
        graphics.fillRect(20, 20, 200, 400);
        
    }
    private void drawSquare(Graphics graphics, int x, int y) {
        Color color=new Color(149, 218, 225);
        graphics.setColor(color);
        graphics.fillRect(x + 1, y , 20 - 1, 20 - 1);
        
        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x+19, y);
        graphics.drawLine(x+19, y, x+19, y+19);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x, y + 19, x, y + 1);
        
       

    }

}
