

package tetrisGame;

import java.util.Random;
import java.lang.Math;
/**
 *
 * @author joaijala
 * Tämä luokka hoitaa tippuvien tetromiinojen toteutuksen ja pitää huolen tetromiinon palikoiden siäisistä palikoista
 */
public class Tetromino {
    
    public enum Shape { Empty , ZShape, SShape, IShape, TShape, OShape, LShape, JShape}
    
    private Shape tetrominoShape; // Tetromiinon muoto (Shape)
    private int [][] tetrominoCords; //sisältää tetromiinon sisäiset kordinaatit oman origon suhteen (esim pyörittäessä)
    
    //Taulukko jossa on kaikkien tetrominon palojen alkukordinaatit sisäisen origon suteen
    private final int[][][] cordinateTable=new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };

    
    //Konstruktori, joka luo uuden tyhjän tetrominon
    public Tetromino() {

        this.tetrominoCords = new int[4][2];
        setTetrominoCords(Shape.Empty);

    }
    
    //Konstruktori, joka luo uuden tetrominon, jonka muoto on annettu parametrina (shape)
    public Tetromino(Shape shape){
        this.tetrominoCords = new int[4][2];
        setTetrominoCords(shape);
    }
    
    //Konstruktori, joka luo uuden tetrominon, jonka muoto on annettu shape eunumin valuena
    public Tetromino(int shape){
        if (shape>7||shape<0)
            return;
        this.tetrominoCords = new int[4][2];
        Shape [] shapes=Shape.values();
        setTetrominoCords(shapes[shape]);
    }
    
    //Asettaa tetrominolle palojen kordinaatit sen muodon mukaan
    private void setTetrominoCords(Shape shape){
        
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                this.tetrominoCords[i][j] = cordinateTable[shape.ordinal()][i][j];
            }
        }
        this.tetrominoShape = shape;
    }
    
    //Palauttaa tetrominon osan x kordinaatin
    public int getX (int index){
        return this.tetrominoCords[index][0];
    }
    
    //Palauttaa tetrominon osan y kordinaatin
    public int getY (int index){
        return this.tetrominoCords[index][1];
    }
    
    //Palauttaa tetrominon muodon
    public Shape getShape(){
        return this.tetrominoShape;
    }
    
    //Asettaa tetrominontietyn palan x arvoksi halutun
    private void setX(int index, int x){
        tetrominoCords[index][0] = x; 
    }
    
    //Asettaa tetrominon tietyn palan y arvoksi halutun
    private void setY(int index, int y) {
        tetrominoCords[index][1] = y; 
    }
    //Pyörittää tetrominoa vasemmalle (myötäpäivään) sen sisäisten kordinaatiston suhteen
    public void rotateLeft(){
        if (this.tetrominoShape == Shape.OShape)
            return;
        
        int helpX;
        
        for (int i=0; i< 4;++i){
            helpX=getX(i);
            this.setX(i, getY(i));
            this.setY(i,-helpX);
        }
    }
    //Pyörittää tetrominoa oikealle (vastpäivään) sen sisäisen kordinaatiston suhteen
    public void rotateRight(){
        if (this.tetrominoShape == Shape.OShape)
            return;
        
        int helpX;
        
        for (int i=0; i<4;++i){
            helpX=getX(i);
            this.setX(i, -getY(i));
            this.setY(i, helpX);
        }
    }

    
}
