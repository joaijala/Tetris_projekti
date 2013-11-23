/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.GameScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisGame.GameLogic;

/**
 * Ruutu, joka tulee näkyviin kun peli on pausella.
 *
 * @author Josse
 */
public class PauseScreen extends JPanel {

    GameLogic game;

    /**
     *
     * @param game
     */
    public PauseScreen(GameLogic game) {
        this.game = game;

    }

    /**
     * Puiirtää pause ruudun pelikentän päälle.
     *
     * @param graphics
     */
    @Override
    public void paintComponents(Graphics graphics) {
        this.setVisible(game.getIsPaused());
        Color color = new Color(100, 100, 100, 200);
        graphics.setColor(color);
        graphics.fillRect(0, 0, 430, 480);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", 0, 40));
        graphics.drawString("Game Paused", 100, 50);
        graphics.setFont(new Font("Arial", 0, 20));
        graphics.drawString("Resume game by pressing \"p\" or resume", 50, 140);

    }

}
