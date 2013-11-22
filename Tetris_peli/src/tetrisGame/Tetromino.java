package tetrisGame;

import java.util.Random;

/**
 * Tämä luokka hoitaa tippuvien tetromiinojen toteutuksen ja
 * pitää huolen tetromiinon palikoiden sisäisten palikoiden kordinaatistoista
 * @author Josse
 */
public class Tetromino {

    /**
     * Eunumi, joka sisältää kaikki tetrominojen maholliset muodot.
     */
    public enum Shape {Empty,ZShape,SShape,IShape,TShape,OShape,LShape,JShape
    }

    /**
     * Tetromiinon muoto (Shape).
     */
    private Shape tetrominoShape;
    /**
     * sisältää tetromiinon sisäiset kordinaatit oman origon suhteen (esim
     * pyörittäessä).
     */
    private int[][] tetrominoCords;

    /**
     * Taulukko jossa on kaikkien tetrominon palojen alkukordinaatit sisäisen
     * origon suteen.
     */
    private final int[][][] cordinateTable = new int[][][]{
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{1, -1}, {1, 0}, {0, 0}, {0, 1}},
        {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
        {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
        {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, -1}, {1, -1}, {1, 0}, {1, 1}},
        {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
    };

    /**
     * Konstruktori, joka luo uuden tyhjän tetrominon.
     */
    public Tetromino() {

        this.tetrominoCords = new int[4][2];
        setTetrominoCords(Shape.Empty);

    }

    /**
     * Konstruktori, joka luo uuden tetrominon, jonka muoto on annettu
     * parametrina (shape).
     * @param shape uuden tetromiinon muoto
     */
    public Tetromino(Shape shape) {
        this.tetrominoCords = new int[4][2];
        setTetrominoCords(shape);
    }

    /**
     * Konstruktori, joka luo uuden tetrominon, jonka muoto on annettu shape
     * eunumin valuena.
     * @param shape uuden tetrominon muoto
     */
    public Tetromino(int shape) {
        if (shape > 7 || shape < 0) {
            return;
        }
        this.tetrominoCords = new int[4][2];
        Shape[] shapes = Shape.values();
        setTetrominoCords(shapes[shape]);
    }

    /**
     * Asettaa tetrominolle palojen kordinaatit sen muodon mukaan.
     * @param shape tetrominon muoto
     */
    private void setTetrominoCords(Shape shape) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                this.tetrominoCords[i][j] = cordinateTable[shape.ordinal()][i][j];
            }
        }
        this.tetrominoShape = shape;
    }

    /**
     * Muuttaa tetrominon muotoa satunnaiseksi ei Empty muodoksi.
     */
    public void setRandomShape() {
        Random random = new Random();
        int newShape = random.nextInt(7) + 1;
        Shape[] shapes = Shape.values();
        setTetrominoCords(shapes[newShape]);
    }

    /**
     * Asettaa tetrominolle halutun muodon. Tarvitaan kun laitetaan uudeksi
     * tippuvaksi tetromiinoksi vanha seuraavaTetromino.
     *
     * @param shape uusi tetrominon muoto
     */
    public void setShape(Shape shape) {
        
        setTetrominoCords(shape);
    }

    /**
     * Palauttaa tetrominon osan x kordinaatin.
     *
     * @param index monesko tetromiinon palikoist on kyseessä
     * @return palauttaa halutun tetromiinopalan sisäisen kordinaatiston x arvon
     */
    public int getX(int index) {
        return this.tetrominoCords[index][0];
    }

    /**
     * Palauttaa tetrominon osan y kordinaatin.
     *
     * @param index monesko tetromiinon palikoist on kyseessä
     * @return palauttaa halutun tetromiinopalan sisäisen kordinaatiston y arvon
     */
    public int getY(int index) {
        return this.tetrominoCords[index][1];
    }

    /**
     * Palauttaa tetrominon muodon.
     *
     * @return this.tetrominoShape
     */
    public Shape getShape() {
        return this.tetrominoShape;
    }

    /**
     * Asettaa tetrominontietyn palan x arvoksi halutun.
     */
    private void setX(int index, int x) {
        this.tetrominoCords[index][0] = x;
    }

    /**
     * Asettaa tetrominon tietyn palan y arvoksi halutun.
     */
    private void setY(int index, int y) {
        this.tetrominoCords[index][1] = y;
    }

    /**
     * Pyörittää tetrominoa vasemmalle (myötäpäivään) sen sisäisten
     * kordinaatiston suhteen.
     */
    public void rotateLeft() {
        if (this.tetrominoShape == Shape.OShape) {
            return;
        }

        int helpX;

        for (int i = 0; i < 4; ++i) {
            helpX = getX(i);
            this.setX(i, getY(i));
            this.setY(i, -helpX);
        }
    }

    /**
     * Pyörittää tetrominoa oikealle (vastpäivään) sen sisäisen kordinaatiston
     * suhteen.
     */
    public void rotateRight() {
        if (this.tetrominoShape == Shape.OShape) {
            return;
        }

        int helpX;

        for (int i = 0; i < 4; ++i) {
            helpX = getX(i);
            this.setX(i, -getY(i));
            this.setY(i, helpX);
        }
    }

}
