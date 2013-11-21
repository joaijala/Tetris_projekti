/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Johanna
 */
public class MenuScreen extends JPanel {
    private UserInterface frame;
    private Button startButton;
    
    public MenuScreen(UserInterface frame){
        this.frame=frame;
        makeButtons();
        this.startButton.setVisible(true);
    }
    private void makeButtons(){
        startButton = new Button("Start Game");
        startButton.addActionListener(new ActionListener() {
 
           
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                frame.startGame();
                
            }
        });
        startButton.setFont(new Font("Arial",0,20));
        
        
        add(startButton); 
        
    }
   
}
