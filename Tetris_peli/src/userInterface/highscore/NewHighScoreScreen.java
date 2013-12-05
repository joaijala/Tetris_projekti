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
import static java.awt.GridBagConstraints.LINE_START;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import userInterface.UserInterface;

/**
 * Ruutu, joka hoitaa uuden high scoren lisäämisen.
 *
 * @author Josse
 */
public final class NewHighScoreScreen extends JPanel {

    private final int score;
    private Button addScoreButton;
    private JTextField nameField;
    private GridBagConstraints container;
    private final HighScoreManager manager;
    private final UserInterface frame;

    /**
     *
     * @param score Pelin pisteet
     * @param manager highScoreManager
     * @param frame UserInterface
     */
    public NewHighScoreScreen(int score, HighScoreManager manager, UserInterface frame) {
        this.score = score;
        this.manager = manager;
        this.frame = frame;
        createComponents();

    }

    /**
     * Hoitaa buttonin ja text fieldin luomisen.
     */
    public void createComponents() {
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();
        container.anchor = LINE_START;

        makeTextField();
        makeAddScoreButton();

    }

    /**
     * Luo text fieldin, johon pelaaja syöttää nimensä.
     */
    public void makeTextField() {
        container.gridy = 0;
        container.weightx = 1;
        nameField = new JTextField(8);
        container.insets = new Insets(20, 75, 10, 20);
        nameField.setVisible(true);

        add(nameField, container);
    }

    /**
     * Luo buttonin, jolla pisteet lisätään high scoreen.
     */
    public void makeAddScoreButton() {
        this.addScoreButton = new Button("Add Score");
        this.addScoreButton.setFont(new Font("Arial", 0, 30));
        container.insets = new Insets(20, 40, 10, 20);
        container.gridy = 1;
        this.addScoreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText();
                if (name.length() > 8) {
                    name = name.substring(0, 8);
                }
                manager.addScore(name, score);
                frame.setNewHighScore(false);

            }
        });
        this.addScoreButton.setVisible(true);
        add(this.addScoreButton, container);
    }

    /**
     * Hoitaa ruudun piirtämisen.
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        paintBackground(graphics);
        paintText(graphics);

    }

    /**
     * Piirtää ruudun taustan.
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
     * Hoitaa ruudun tekstin piirtämisen.
     *
     * @param graphics
     */
    private void paintText(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 1, 25));
        graphics.drawString("New High Score", 28, 60);
        graphics.setFont(new Font("Arial", 0, 20));
        graphics.drawString("Your Score: " + Integer.toString(score), 28, 100);
        graphics.setFont(new Font("Arial", 1, 14));
        graphics.drawString("Type your name and press", 28, 130);
        graphics.drawString("\"Add Score\"", 28, 150);
        graphics.drawString("name max 8 characters", 28, 175);
    }

}
