package tetrisGame;

import userInterface.gamescreen.GameScreen;

/**
 * Tässä luokassa tapahtuu itse pelilooppi ja koko
 * pelin pyörittäminen.
 *
 * @author Josse
 */
public class GameLogic {

    /**
     * Lista sisältää miten paljo pisteitä tulee mistäkin tyhjennetystä
     * rivimäärästä.
     */
    private final int[] scoresFromRow = {0, 40, 100, 300, 1200};
    private static final double NANOSEC_TO_MILLISEC = 0.000001;
    /**
     * Tippuvan tetrominon origon x kordinaatti.
     */
    private int globalX;
    /**
     * Tippuvan tetrominon origon y kordinaatti.
     */
    private int globalY;
    /**
     * Tetromino, joka on tippumassa pelikentällä.
     */
    private final Tetromino fallingTetromino;
    /**
     * Seuraavaksi tippuva tetromino.
     */
    private final Tetromino nextTetromino;
    /**
     * Pelin pelilaut.
     */
    private final Board board;
    /**
     * Sisältää tiedon onko peli pausella. On true jos peli on pausella.
     */
    private boolean isPaused = false;
    /**
     * Sisältää tiedon onko tetromino juuri tippumassa.
     */
    private boolean isTetrominoFalling = false;
    /**
     * Sisältää tiedon, onko pelilooppi käynnissä. Se on false ennen pelin
     * alkua,tai jos peli on päättynyt.
     */
    private boolean isGameRunning = false;
    /**
     * Sisältää tiedon, jos pelaaja on lopettanut pelin painamalla main menu
     * nappia.
     */
    private boolean endGame = false;
    /**
     * Pitää kirjaa onko pelaaja liikuttanut palikkaa. Se on positiivinen jos
     * liikutettu oikealle, negatiivinen jos vasemmalle ja 0 ei ole liikutettu.
     */
    private int isMoved;
    /**
     * Pitää kirjaa onko pelaaja pyörittänyt palikkaa. Se on poitiivinen jos on
     * pyöritetty oikealle ja neggatiivinen jos on pyöritetty oikealle.
     */
    private int isRotated;
    /**
     * softDrop antaa peliaajan tiputtaa tetrominon yhden alaspäin.
     */
    private int softDrop;
    /**
     * tells the number of soft drops done for tetormino.
     */
    private int softDropsDone;
    /**
     * dropDown antaa pelaajan tiputtaa tetrominon kokonaan alas.
     *
     */
    private boolean dropDown;
    /**
     * Tyhjennettyjen rivien määrä.
     */
    private int clearedRows;
    /**
     * Nopeus, jolla tetromiinot tippuvat alaspäin.
     */
    private double dropIntervall;
    /**
     * Pelin leveli. Alkaa nollasta korkein level on 11
     */
    private int level;
    /**
     * Pelaajan pisteet tässä pelissä.
     */
    private int score;
    /**
     * Sisältää ajan, jolloin tetromino tippui viimeksi yhden askelman alaspäin.
     */
    private double timeOfLastDrop;
    /**
     * Pelin käyttämä peliruutu.
     */
    private final GameScreen gameScreen;

    /**
     * construktori alustaa uuden tyhjän pelin.
     */
    public GameLogic() {
        this.board = new Board();
        this.fallingTetromino = new Tetromino();
        this.nextTetromino = new Tetromino();
        this.clearedRows = 0;
        this.dropIntervall = 500;
        this.gameScreen = new GameScreen(this);
        this.level = 0;
        this.score = 0;

    }

    /**
     * gameloop pyöeittää itse pelin pelilooppia.
     *
     * @return pelin pisteet
     */
    public int gameLoop() {
        this.isGameRunning = true;
        this.gameScreen.repaint();
        delay(2000);
        this.isGameRunning = true;
        setNewFallingTetromino();
        setNewFallingTetromino();
        /*Pelilooppi*/
        while (this.isGameRunning) {

            /*jos peli on pausella pelilooppi odottaa että peli ei ole enää pausella*/
            if (this.isPaused) {
                gameScreen.repaint();
                waitUntillNotPaused();
                gameScreen.repaint();
            }
            /**
             * jos on kulunut tarpeeksi aikaa edellisestä tetrominon
             * tiputtamisesta tetromino tippuu alaspäin
             */
            if (this.isTetrominoFalling && getCurrentTimeInMilliseconds() > (this.dropIntervall + this.timeOfLastDrop)) {
                dropOneLineDown();
            }
            /**
             * jos pelaaja on liikuttanut tetrominoa peli siitää sitä jos
             * mahdollista
             */
            if (this.isMoved != 0) {
                moveTetromino();
            }
            /**
             * jos pelaaja on pyörittänyt kappaletta,peli pyörittää sitä tässä
             * jos mahdollista
             */
            if (this.isRotated != 0) {
                rotateTetromino();
            }
            /**
             * jos pelaaja on painanut softDroppia tetromino tippuu yhden
             * askeleen alaspäin;
             */
            if (softDrop > 0) {
                dropOneLineDown();
                this.softDropsDone++;
                softDrop--;
            }
            /**
             * jos pelaaja on painanut dropDown tetromino tippuu alas kunnes se
             * ei voi enää tippuu alas
             */
            if (this.dropDown) {
                while (dropDown) {
                    dropOneLineDown();
                    this.softDropsDone++;
                }
            }

        }
        /*jos peli lopetetaan paianmmalla main menu pause ruudussa pisteet häviää*/
        if (endGame) {
            return 0;
        }
        /*Pelin loputtua peli viipyy hetken, jotta pelaaja ehtii nähdä, että kuoli*/
        delay(2000);
        return score;
    }

    /**
     * asettaa fallingTetromiinolle seuraavan Tetrominon muodon,
     * nextTetrominolle uuden random muodon sekä nollaa liikuttamiseen liittyvät
     * muututjat.
     */
    public void setNewFallingTetromino() {
        this.globalX = 4;
        this.globalY = 1;
        this.fallingTetromino.setShape(this.nextTetromino.getShape());
        this.nextTetromino.setRandomShape();
        /*peli loppuu jos ei voi laittaa uutta palikkaa*/
        if (!isMovePossible(this.fallingTetromino, globalX, globalY)) {
            this.isGameRunning = false;
        }
        this.isMoved = 0;
        this.isRotated = 0;
        this.softDrop = 0;
        this.softDropsDone = 0;
        this.dropDown = false;
        this.isTetrominoFalling = true;
        this.timeOfLastDrop = getCurrentTimeInMilliseconds();
        gameScreen.repaint();
    }

    /**
     * DropOneLineDown pudottaa tetrominon alaspäin jos se on mahdollista,
     * muuten asettaa uuden tetrominon pelilaudan huipulle.
     */
    public void dropOneLineDown() {
        if (isMovePossible(this.fallingTetromino, this.globalX, this.globalY + 1)) {
            this.globalY++;
            this.timeOfLastDrop = getCurrentTimeInMilliseconds();
            gameScreen.repaint();
        }
        else {
            this.isTetrominoFalling = false;
            this.board.setTetrominoToBoard(globalX, globalY, this.fallingTetromino);
            takeCareOfFullLines();
            setNewFallingTetromino();

        }

    }

    /**
     * Huolehtii täysinäisten rivien tarkistamisesta ja niiden poistamisesta.
     *
     */
    public void takeCareOfFullLines() {
        this.gameScreen.repaint();
        delay(5);
        int fullLines = this.board.checkWhatLinesAreFull();
        if (fullLines != 0) {
            delay(200);
            this.gameScreen.repaint();
            delay(300);
            this.board.removeFullLines();
            this.clearedRows += fullLines;
            seIfLevelChange();

        }
        addScores(fullLines);
        this.gameScreen.repaint();

    }

    /**
     * Antaa pisteitä aina kun pala on tippunut valmiiksi
     *
     * @param removedLines poistettujen rivien määrä
     */
    public void addScores(int removedLines) {
        int scores = this.scoresFromRow[removedLines];
        this.score += this.softDropsDone + ((this.level + 1) * scores);
    }

    /**
     * Jos levelin vaihdon kriteeri täyttyy, leveli vaihtuu ja dropIntervall
     * pienenee.
     */
    public void seIfLevelChange() {
        if (this.clearedRows >= (this.level + 1) * 10 && this.level < 11) {
            this.level++;
            this.dropIntervall -= 50;
        }
    }

    /**
     * MoveTetromino hoitaa tetrominon liikuttamisen sivulle.
     */
    public void moveTetromino() {
        if (this.isMoved > 0) {
            if (isMovePossible(this.fallingTetromino, this.globalX + 1, this.globalY)) {
                this.globalX++;
                this.isMoved--;

            }
        }
        if (this.isMoved < 0) {
            if (isMovePossible(this.fallingTetromino, this.globalX - 1, this.globalY)) {
                this.globalX--;
                this.isMoved++;

            }
        }
        gameScreen.repaint();
    }

    /**
     * isMovePossible tarkistaa, onko aijottu siirto mahdollinen. Jos se ei ole
     * mahdollinen palautuu false, muuten true,
     */
    private boolean isMovePossible(Tetromino tetromino, int origoX, int origoY) {
        int[][] boardStatus = board.getBoardStatus();
        boolean isTrue = true;
        if (origoX > 9) {
            return false;
        }
        for (int i = 0; i < 4; i++) {

            if (((tetromino.getY(i) + origoY) > 19) || ((tetromino.getY(i) + origoY) < 0)) {
                return false;
            }
            else if (((tetromino.getX(i) + origoX) > 9) || ((tetromino.getX(i) + origoX) < 0)) {
                return false;
            }
            else if (boardStatus[(tetromino.getY(i) + origoY)][(tetromino.getX(i) + origoX)] != 0) {
                return false;
            }

        }
        return isTrue;
    }

    /**
     * Hoitaa tetrominon pyörittämisen, jos se on mahdollista.
     */
    public void rotateTetromino() {

        if (this.isRotated > 0) {
            this.fallingTetromino.rotateRight();
            if (isMovePossible(fallingTetromino, globalX, globalY)) {
                this.gameScreen.repaint();
                this.isRotated--;
                return;
            }
            else if (!manageWallKick()) {
                this.fallingTetromino.rotateLeft();
            }
            this.isRotated--;

        }
        else if (this.isRotated < 0) {
            this.fallingTetromino.rotateLeft();
            if (isMovePossible(fallingTetromino, globalX, globalY)) {
                this.gameScreen.repaint();
                this.isRotated++;
                return;
            }
            else if (!manageWallKick()) {
                this.fallingTetromino.rotateRight();
            }

            this.isRotated++;

        }
        this.gameScreen.repaint();

    }

    /**
     * Liikuttaa tetromiinon pois esteen vierestä, jos mahdollista, niin että
     * pyörittäminen on mahdollista. 
     *
     * @param amount määrä paljonko siirretään sivulle
     * @return true jos wallKick on mahdollista, muuten false
     */
    private boolean doWallKick(int amount) {
        /*try wallKick right*/
        if (isMovePossible(this.fallingTetromino, globalX + amount, globalY)) {
            this.globalX += amount;
            return true;

        } /*try wallKick left*/

        else if (isMovePossible(this.fallingTetromino, globalX - amount, globalY)) {
            this.globalX -= amount;
            return true;
        }
        return false;
    }

    /**
     * Ohjaa doWallKickiä. Tämä on mitä kutsutaan kun halutaan tehdä wallkikki.
     * 
     *
     * @return true jos onnistuu, muuten false
     */
    private boolean manageWallKick() {

        if (doWallKick(1)) {
            return true;
        }
        else if (this.fallingTetromino.getShape().ordinal() == 3 && doWallKick(2)) {
            return true;
        }
        return false;
    }
    /**
     * Ei tee mitään niin kauan kuin peli on tauolla. Odottamisen tarkoitus on,
     * että näppäinkuuntelija pääsee väliin reagoimaan
     */
    public void waitUntillNotPaused() {
        while (isPaused) {
            delay(10);
        }
    }

    /**
     * Hoitaa pelissä tapahtuvat tauot
     *
     * @param delay tauon kesto millisekunneissa
     */
    private void delay(int delay) {
        double time = getCurrentTimeInMilliseconds();
        while (getCurrentTimeInMilliseconds() < time + delay) {
            //wait;
        }
    }

    /**
     *
     * @return fallingTetromino
     */
    public Tetromino getFallingTetromino() {
        return this.fallingTetromino;
    }

    /**
     *
     * @return nextTetromino
     */
    public Tetromino getNextTetromino() {
        return this.nextTetromino;
    }

    /**
     *
     * @return board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     *
     * @return globalX
     */
    public int getGlobalX() {
        return this.globalX;
    }

    /**
     *
     * @return globalY
     */
    public int getGlobalY() {
        return this.globalY;
    }

    /**
     * Kertoo, onko peli pausella
     *
     * @return isPaused
     */
    public boolean getIsPaused() {
        return this.isPaused;
    }

    /**
     * Kertoo, onko peli käynnissä
     *
     * @return isGameRunning
     */
    public boolean getIsGameRunning() {
        return this.isGameRunning;
    }

    /**
     *
     *
     * @return isTetrominoFalling
     */
    public boolean getIsTetrominoFalling() {
        return this.isTetrominoFalling;
    }


    /**
     *
     *
     * @return gameScreen
     */
    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    /**
     *
     * @return isMoved
     */
    public int getIsMoved() {
        return this.isMoved;
    }

    /**
     *
     * @return isRotated
     */
    public int getIsRotated() {
        return this.isRotated;
    }

    /**
     *
     * @return softDrop
     */
    public int getSoftDrop() {
        return this.softDrop;
    }

    /**
     *
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return getScore
     */
    public int getScore() {
        return this.score;
    }

    /**
     *
     *
     * @return clearedRows
     */
    public int getClearedRows() {
        return this.clearedRows;
    }

    /**
     *
     * @return palauttaa javan virtuaalikoneen tämänhetkisen ajan
     * millisekuntteina.
     */
    private double getCurrentTimeInMilliseconds() {
        return System.nanoTime() * NANOSEC_TO_MILLISEC;
    }

    /**
     * controllListener muuttaa tämän avulla isMoved arvoa.
     *
     * @param i liikuttamisen määrä ja suunta
     */
    public synchronized void setIsMoved(int i) {
        this.isMoved = i;
    }

    /**
     * controllListener muuttaa tämän avulla isRotated arvoa.
     *
     * @param i pyörittämisen määrä ja suunta
     */
    public synchronized void setIsRotated(int i) {
        this.isRotated = i;
    }

    /**
     * controllListener muuttaa tämän avulla softDrop arvoa.
     *
     * @param i soft droppien määrä
     */
    public synchronized void setSoftDrop(int i) {
        this.softDrop = i;
    }

    /**
     * ControllListener muuttaa tämän avulla onko peli pausella.
     */
    public synchronized void setIsPaused() {
        this.isPaused = !this.isPaused;
    }

    /**
     * ControllListener muuttaa tämän avulla tartteeko pala tiputtaa alas.
     */
    public synchronized void setDropDownTrue() {
        this.dropDown = true;
    }

    /**
     * Jos peli halutaan keskeyttää kesken suorituksen kutsutaan tätä.
     */
    public void setEndGame() {
        this.isGameRunning = false;
        this.endGame = true;

    }
}
