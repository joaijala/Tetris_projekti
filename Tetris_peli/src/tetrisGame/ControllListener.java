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
    private int isMoved;
    private GameLogic game;
    public ControllListener(GameLogic game){
        this.game=game;
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_LEFT){
            game.setIsMoved(-1);
        }
        else if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
            game.setIsMoved(1);
        }
        else if(ke.getKeyCode()==KeyEvent.VK_UP||ke.getKeyCode()==KeyEvent.VK_X){
            game.setIsRotated(1);
        }
        else if(ke.getKeyCode()==KeyEvent.VK_Z){
            game.setIsRotated(-1);
        }
        else if (ke.getKeyCode()==KeyEvent.VK_DOWN){
            game.setSoftDrop(1);
        }
        else if (ke.getKeyCode()==KeyEvent.VK_SPACE){
            game.setDropDownTrue();
        }
        
        if(ke.getKeyCode()==KeyEvent.VK_P){
            game.setIsPaused();
        }
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }

    public int getIsMoved(){
        return this.isMoved;
    }



    
}
