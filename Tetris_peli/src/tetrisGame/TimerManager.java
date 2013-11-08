/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Johanna
 */
public class TimerManager implements ActionListener {
    public Timer timer;
    public GameLogic game;
    
    public TimerManager(int delay, GameLogic game){
        this.timer=new Timer(400,this);
        this.game=game;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        game.GameLoop();
    }
    
}
