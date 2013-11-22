package tetrisGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tämä luokka on pelin controllisteneri, joka kuuntelee pelaajan
 * näppäimistöpainalluksiia ja kertoo niistä GameLogikille
 *
 * @author Josse
 */
public class ControllListener implements KeyListener {

    private int isMoved;
    private final GameLogic game;

    /**
     *
     * @param game GameLogic, jota ohjataan
     */
    public ControllListener(GameLogic game) {
        this.game = game;

    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    /**
     * Jos peli on pällä se kuuntelee aina pausen painamista, jos peli ei ole
     * pausella se kuuntelee myös pelin ohjauspainkikkeita.
     *
     * @param ke painettu näppäin
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (game.getIsGameRunning()) {
            if (ke.getKeyCode() == KeyEvent.VK_P) {
                game.setIsPaused();
            }
            else if (!game.getIsPaused()) {
                if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    game.setIsMoved(-1);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.setIsMoved(1);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_X) {
                    game.setIsRotated(1);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_Z) {
                    game.setIsRotated(-1);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    game.setSoftDrop(1);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    game.setDropDownTrue();
                }
            }

        }

    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {

    }

}
