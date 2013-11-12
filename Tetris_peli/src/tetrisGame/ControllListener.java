/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Johanna
 */
public class ControllListener implements KeyListener {
    private GameLogic game;
    
    public ControllListener(GameLogic game){
        this.game=game;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode()==KeyEvent.VK_A){
            this.game.isMoved--;
        }
        else if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
            this.game.isMoved++;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
