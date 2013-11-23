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
import static java.awt.GridBagConstraints.SOUTHWEST;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import sounds.GameMusic;

/**
 * Main menu ruutu.
 *
 * @author Josse
 */
public class MenuScreen extends JPanel {

    private final UserInterface frame;
    private Button startButton;
    private Button quitButton;
    private Button highScoreButton;
    private Button helpScreenButton;
    private final GridBagConstraints container;
    private JButton soundButton;
    GameMusic music;

    /**
     * Konstruktorissa luodaan main menuun nappulat.
     *
     * @param frame UserInterface
     */
    public MenuScreen(UserInterface frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();
        music = new GameMusic("Mic_music_tetris");
        music.playMusic();
        this.makeButtons();

    }

    /**
     * Luo main menuuseen tarvittavat buttonit.
     */
    private void makeButtons() {

        makeStartButton();
        makeHighscoreButton();
        makeHelpScreenButton();
        makeQuitButton();
        
        makeSoundButton();

    }

    /**
     * Luo buttonin josta musiikin saa päälle ja pois-
     */
    private void makeSoundButton() {
        this.soundButton = new JButton("Sound off");
        GridBagConstraints container2 = new GridBagConstraints();
        container2.gridy = 4;
        container2.gridx = 1;
        container2.insets = new Insets(50, 100, 0, 0);
        container2.anchor = GridBagConstraints.NORTHEAST;
        container2.weighty = 0;
        soundButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (music.isPlaying()) {
                    music.stopMusic();
                    soundButton.setText("Sound on");
                    validate();
                }
                else {
                    music.playMusic();
                    soundButton.setText("Sound off");
                    validate();
                }

            }
        });
        soundButton.setVisible(true);

        this.add(soundButton, container2);
    }

    /**
     * Luo Start Game buttonin.
     */
    private void makeStartButton() {
        startButton = new Button("Start Game");
        startButton.setFont(new Font("Arial", 0, 30));
        container.insets = new Insets(10, 40, 10, 10);
        container.gridy = 0;
        container.weighty = 0;
        container.weightx = 5;
        container.anchor = SOUTHWEST;
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setStartnewGame();

            }
        });
        add(startButton, container);
    }

    /**
     * Luo high score buttonin.
     */
    private void makeHighscoreButton() {
        highScoreButton = new Button("High Score");
        highScoreButton.setFont(new Font("Arial", 0, 30));
        container.gridy = 1;
        highScoreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setShowHighscore(true);
            }
        });
        add(highScoreButton, container);
    }
    /**
     * Luo buttonin jolla pääsee help screeniin.
     */
    private void makeHelpScreenButton(){
        this.helpScreenButton=new Button("     Help     ");
        helpScreenButton.setFont(new Font("Arial", 0, 30));
        container.gridy = 2;
        helpScreenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setShowHelpScreen(true);
            }
        });
        helpScreenButton.setVisible(true);
        add(helpScreenButton,container);
    }

    /**
     * Luo quit game buttonin.
     */
    private void makeQuitButton() {
        quitButton = new Button("Quit Game");
        quitButton.setFont(new Font("Arial", 0, 30));
        container.gridy = 3;
        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                System.exit(0);
            }
        });

        add(quitButton, container);
    }

    /**
     * Hallitsee main menuun taustan ja tekstien piirtämisen.
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        paintBackground(graphics);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 0, 35));
        graphics.drawString("Main Menu", 40, 60);
    }

    /**
     * Piirtää main menuun taustan.
     *
     * @param graphics
     */
    public void paintBackground(Graphics graphics) {
        double screemHeight = 480;
        double screenWidth = 430;
        int squareSize = 20;
        for (int x = 0; x < (screenWidth / squareSize); x++) {
            for (int y = 0; y < (screemHeight / squareSize); y++) {
                drawSquare(graphics, x * squareSize, y * squareSize);
            }
        }
        graphics.setColor(Color.black);
        graphics.fillRect(15, 15, 210, 410);
        graphics.setColor(Color.white);
        graphics.fillRect(20, 20, 200, 400);

    }

    /**
     * Piirtää yhden ruudun taustasta.
     *
     * @param graphics
     * @param x ruudun ylävasemman kulman x kordinaatti.
     * @param y ruudun ylävasemman kulman y kordinaatti.
     */
    private void drawSquare(Graphics graphics, int x, int y) {
        Color color = new Color(149, 218, 225);
        graphics.setColor(color);
        graphics.fillRect(x + 1, y, 19, 19);

        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x + 19, y);
        graphics.drawLine(x + 19, y, x + 19, y + 19);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x, y + 19, x, y + 1);

    }

}
