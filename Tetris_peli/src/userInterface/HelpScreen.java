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
import static java.awt.GridBagConstraints.LINE_END;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Help ruutu.
 * @author Josse
 */
public final class HelpScreen extends JPanel
{
    private Button mainMenuButton;
    private GridBagConstraints container;
    private final UserInterface frame;
    
    /**
     *
     * @param frame
     */
    public HelpScreen(UserInterface frame) {
        this.frame = frame;
        makeButton();

    }

    /**
     * Luo helpScreenin buttonit.
     */
    public void makeButton() {
        this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();

        makeMainmenuButton();
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

                frame.setShowHelpScreen(false);

            }
        });
        add(mainMenuButton, container);
    }
    
    /**
     * Hallitsee Help Screenin taustan ja tekstien piirtämisen.
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        paintBackground(graphics);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 0, 35));
        graphics.drawString("Help", 80, 60);
        drawText(graphics);
    }
    /**
     * hoitaa tekstin piirtämisen.
     * @param graphics 
     */
    
    public void drawText(Graphics graphics){
        graphics.setFont(new Font("Arial", 0, 13));
        graphics.drawString("Welcome to Tetris!", 22, 85);
        graphics.drawString("Press \"Start Game\" to start the", 22, 105);
        graphics.drawString("game", 22, 120);
        graphics.drawString("In game press:", 22, 135);
        graphics.drawString("- <- and -> to move left and right", 22, 150);
        graphics.drawString("- Space to drop tetromino down", 22, 165);
        graphics.drawString("- Arrow down to move tetromino", 22, 180);
        graphics.drawString("  1 down", 22, 195);
        graphics.drawString("- Z to rotate tetromino left and", 22, 210);
        graphics.drawString("  arrow up or X to rotate right", 22, 225);
        graphics.drawString("- P to pause the game", 22, 240);
        graphics.drawString("- If you press main menu when", 22, 255);
        graphics.drawString("  game is paused progress will be ", 22, 270);
        graphics.drawString("  lost", 22, 285);
        
        graphics.drawString("Try to clear lines by filling them", 22, 305);
        graphics.drawString("Game Over when board gets full", 22, 320);
        
        graphics.drawString("You can turn off the music by ", 22, 340);
        graphics.drawString("pressing \"sound off\" in main menu", 22, 355);
        
        graphics.drawString("Enjoy playing! ", 22, 380);
        
        graphics.setFont(new Font("Arial", 1, 15));
        graphics.drawString("Made by Johanna Äijälä", 245, 335);
        graphics.setFont(new Font("Arial", 1, 14));
        graphics.drawString("Music: \"Mic music tetris !\" ", 235, 355);
        graphics.drawString("by Mic", 300, 375);
        
    }

    /**
     * Piirtää help Screenin taustan.
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
