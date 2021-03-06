/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.highscore;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.LINE_END;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import userInterface.UserInterface;

/**
 * Ruutu, joka näyttää high scoret.
 *
 * @author Josse
 */
public final class HighScoreScreen extends JPanel {

    private final HighScoreManager highScore;
    private GridBagConstraints container;
    private Button mainMenuButton;
    //private Button clearScoresButton;
    private final UserInterface frame;

    /**
     * Luo uuden high score ruudon.
     *
     * @param manager
     * @param frame
     */
    public HighScoreScreen(HighScoreManager manager, UserInterface frame) {
        highScore = manager;
        this.frame = frame;
        makeButton();

    }

    /**
     * Luo high score screenin buttonit.
     */
    public void makeButton() {
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();

        makeMainmenuButton();
        /* makeClearHighScoreButton();*/
    }

    /**
     * Luo mainMenu buttonin.
     */
    public void makeMainmenuButton() {
        mainMenuButton = new Button("Main menu");
        mainMenuButton.setFont(new Font("Arial", 0, 30));
        container.insets = new Insets(20, 40, 10, 20);
        container.anchor = LINE_END;
        container.weightx = 5;
        container.gridy = 0;
        mainMenuButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                frame.setShowHighscore(false);

            }
        });
        add(mainMenuButton, container);
    }

    /**
     * Luo buttonin, jolla voi tyhjentää high scoren sisällön poissa käytöstä
     *
     *
     * public void makeClearHighScoreButton(){
     * this.clearScoresButton=new Button("Clear Scores");
     * this.clearScoresButton.setFont(new Font("Arial", 0, 30));
     * container.insets = new Insets(20, 40, 10, 10);
     * container.gridy = 1;
     * this.clearScoresButton.addActionListener(new ActionListener() {
     *
     * @param graphics
     * @Override
     * public void actionPerformed(ActionEvent ae) {
     *
     * highScore.clearScores();
     *
     * }
     * });
     * add(this.clearScoresButton, container);
     * }
     */

    /**
     * Piirtää näkyviin high score ruudun osat.
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        paintBackground(graphics);
        paintScores(graphics);
    }

    /**
     * Piirtää high score ruudun taustan.
     *
     * @param graphics
     */
    public void paintBackground(Graphics graphics) {
        double height = 480;
        double width = 430;
        for (int x = 0; x < (width / 20); x++) {
            for (int y = 0; y < (height / 20); y++) {
                drawSquare(graphics, x * 20, y * 20);
            }
        }
        graphics.setColor(Color.black);
        graphics.fillRect(15, 15, 210, 410);
        graphics.setColor(Color.white);
        graphics.fillRect(20, 20, 200, 400);

    }

    /**
     * Piirtää yhden pelilaudan taustan ruuduista.
     *
     * @param graphics
     * @param x ruudun ylä vasemman kulman x kordinaatti
     * @param y ruudun ylä vasemman kulman y kordinaatto
     */
    private void drawSquare(Graphics graphics, int x, int y) {
        Color color = new Color(149, 218, 225);
        graphics.setColor(color);
        graphics.fillRect(x + 1, y, 20 - 1, 20 - 1);

        graphics.setColor(color.brighter());
        graphics.drawLine(x, y, x + 19, y);
        graphics.drawLine(x + 19, y, x + 19, y + 19);

        graphics.setColor(color.darker());
        graphics.drawLine(x + 1, y + 19, x + 19, y + 19);
        graphics.drawLine(x, y + 19, x, y + 1);

    }

    /**
     * Hoitaa high score screenin tekstiosion piirtämisen.
     *
     * @param graphics
     */
    public void paintScores(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 0, 35));
        graphics.drawString("High Score", 40, 60);
        int amount = this.highScore.getScores().size();
        graphics.setFont(new Font("Arial", 0, 20));
        for (int i = 0; i < amount; i++) {
            Score score = this.highScore.getScore(i);
            graphics.drawString(Integer.toString(i + 1) + ".", 27, 100 + i * 30);
            graphics.drawString(score.getName(), 57, 100 + i * 30);
            graphics.drawString(Integer.toString(score.getScore()), 150, 100 + i * 30);
        }
    }

}
