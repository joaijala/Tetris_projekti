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
        this.setBackground(new Color(149, 218, 225));
        this.
                makeButtons();

    }

    private void makeButtons() {
        makeStartButton();

        makeHighscoreButton();
        
        makeQuitButton();

    }

    private void makeStartButton() {
        startButton = new Button("Start Game");
        startButton.setFont(new Font("Arial", 0, 30));
        startButton.setBounds(100, 100, 100, 20);
        container.insets = new Insets(10, 10, 10, 10);
        container.gridy = 0;
        container.anchor = CENTER;
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

}
