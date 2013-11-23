/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import userInterface.GameScreen.GameScreen;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetrisGame.GameLogic;
import tetrisGame.ControllListener;
import userInterface.HighScore.HighScoreManager;
import userInterface.HighScore.HighScoreScreen;
import userInterface.HighScore.newHighScoreScreen;

/**
 * Käyttöliittymän perusta, joka hoitaa ohjelman pyörittämisen
 *
 * @author Josse
 */
public final class UserInterface extends JFrame implements Runnable {

    /**
     * Pelin aikainen ruutu.
     */
    private GameScreen gameScreen;
    private GameLogic game;
    private newHighScoreScreen newScoreScreen;
    private MenuScreen menuScreen;
    private HighScoreManager highScores;
    private HighScoreScreen highScoreScreen;
    private HelpScreen helpScreen;
    /**
     * Jos tämä on true käynnistyy uusi peli.
     */
    private boolean startNewGame = false;
    /**
     * Jos tämä on true näyttää ohjelma high score ruudun.
     */
    private boolean showHighscore = false;

    /**
     * Jos tämä on true on tullut uusi high score. Tällöin High Score ruutu
     * tulee näkyviin.
     */
    private boolean newHighScore = false;
    /**
     * Jos tämä on true näytetään help screen.
     */
    private boolean showHelpScreen=false;
    
    /**
     * edellisestä pelistä saadut pisteet. Kertoo tuleeko uusi high score.
     */
    private int score = 0;

    /**
     * Luo uuden UserInterfacen ja kaikki tarvittavat komponentit, sekä laittaa
     * näkyväksi ruuduksi main menun
     */
    public UserInterface() {

        setTitle("Tetris");
        setPreferredSize(new Dimension(430, 480));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponent();
        setContentPane(menuScreen);
        pack();
        setVisible(true);
        setFocusable(true);
        

    }

    /**
     * Tässä on toteutettu ohjelmalooppi.
     */
    @Override
    public void run() {
        while (true) {
            if (startNewGame) {
                
                startGame();
            }
            if (newHighScore) {
                manageNewHighScore();
            }
            if (showHighscore) {
                manageShowHighScore();
            }
            if(showHelpScreen){
                manageHelpScreen();
            }
            delay(10);
        }
    }

    /**
     * Luo kaikki komponentit, mitkä pitäisi olla kun ohjelma käynistyy.
     */
    private void createComponent() {
        menuScreen = new MenuScreen(this);
        highScores = new HighScoreManager();
        highScoreScreen = new HighScoreScreen(highScores, this);
        
        
    }

    /**
     * Kun pelaaja on help screenissä tämä hallinnoi mitä ruutua näytretään.
     */
    public void manageHelpScreen(){
        this.helpScreen=new HelpScreen(this);
        setContentPane(this.helpScreen);
        setVisible(true);
        while (showHelpScreen) {
            delay(10);
        }
        setContentPane(menuScreen);
        setVisible(true);
    }
    /**
     * Kun pelaaja saa uuden high scoren tämä hallinnoi mitä ruutua näytetään.
     */
    public void manageNewHighScore() {
        this.newScoreScreen = new newHighScoreScreen(this.score, this.highScores, this);
        setContentPane(newScoreScreen);
        setVisible(true);
        while (newHighScore) {
            delay(10);
        }
        
    }

    /**
     * Tämä hallinnoi, mitä ruutua näytetään,kun pelaaja katsoo high scorea.
     */
    public void manageShowHighScore() {
        setContentPane(highScoreScreen);
        setVisible(true);
        while (showHighscore) {
            delay(10);
        }
        setContentPane(menuScreen);
        setVisible(true);
    }

    /**
     * luo uuden pelin, laittaa sen näkyville ruutuun ja käynnistää peliloopin.
     */
    public void startGame() {
        game = new GameLogic();
        this.gameScreen = game.getGameScreen();
        ControllListener listener = new ControllListener(game);
        addKeyListener(listener);
        setContentPane(gameScreen);
        setVisible(true);
        startNewGame = false;
        this.score = game.gameLoop();
        checkForHighScore();
    }

    /**
     * Tarkistaa tuliko uusi higscore. Jos tuli uusi high score laitetaan
     * newhighScore trueks, jotta ohjelma avaa newHighScore ruudun. Jos score on
     * 0 mennään suoraan main menuseen, sillä tällöin peli on lopetettu kesken.
     */
    public void checkForHighScore() {
        if (this.score == 0) {
            setContentPane(menuScreen);
            setVisible(true);
            return;
        }
        if (this.score > this.highScores.getSmallestScore()) {
            this.newHighScore = true;
        }
        this.showHighscore = true;

    }

    /**
     * laittaa kutsujathreadin odottamaan looppiin kunnes aika on kulunut
     *
     * @param delay -aika miten kauan pitäisi odottaa
     */
    private void delay(int delay) {
        double time = getCurrentTimeInMilliseconds();
        while (getCurrentTimeInMilliseconds() < time + delay) {
            //wait;
        }
    }

    /**
     *
     * @return javan virtuaalikoneen tämänhetkinen aika millisekunneissa
     */
    private double getCurrentTimeInMilliseconds() {
        return System.nanoTime() * 0.000001;
    }

    /**
     *
     * @return palautta userInterface JFramen
     */
    public JFrame getFrame() {
        return this;
    }

    /**
     * asettaa startnewGame trueks jotta runin ohjelmalooppi voi käynnistää
     * pelin.
     */
    public void setStartnewGame() {
        this.startNewGame = true;
    }
    
    /**
     * Muttaa setShorHelpScreen arvon.
     * @param status haluttu arvo
     */
    public void setShowHelpScreen(boolean status){
        this.showHelpScreen=status;
    }

    /**
     * Muuttaa showHighscoren arvon halutuksi.
     *
     * @param status
     */
    public void setShowHighscore(boolean status) {
        this.showHighscore = status;
    }

    /**
     * Muuttaa newHighScoren arvon halutuksi.
     *
     * @param status
     */
    public void setNewHighScore(boolean status) {
        this.newHighScore = status;
    }

}
