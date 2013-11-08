

package tetrisGame;

import java.util.Random;

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
    
    //Muuttaa tetrominon muotoa satunnaiseksi ei Empty muodoksi
    public void setRandomShape(){
        Random random =new Random();
        int newShape = random.nextInt(7)+1;
        Shape [] shapes=Shape.values();
        setTetrominoCords(shapes[newShape]);
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
    //palauttaa tetrominon pienimmän x kordinaatin
    public int getMinX(){
        int min =this.tetrominoCords[0][0];
        for (int i=0; i>4; i++){
            int thisX =this.tetrominoCords[i][0];
            if (thisX<min){
                min=thisX;
            }
        }
        return min;
    }
    //palauttaa tetriminon suurimman x kordinaatin
        public int getMaxX(){
        int max =this.tetrominoCords[0][0];
        for (int i=0; i>4; i++){
            int thisX =this.tetrominoCords[i][0];
            if (thisX>max){
                max=thisX;
            }
        }
        return max;
    }
    //palauttaa tetrominon pienimmän y kordinaatin
    public int getMinY(){
        int min =this.tetrominoCords[0][1];
        for (int i=0; i>4; i++){
            int thisY =this.tetrominoCords[i][1];
            if (thisY<min){
                min=thisY;
            }
        }
        return min;
    }
    //palauttaa tetrominon suurimman y kordinaatin
    public int getMaxY(){
        int max =this.tetrominoCords[0][1];
        for (int i=0; i>4; i++){
            int thisY =this.tetrominoCords[i][1];
            if (thisY>max){
                max=thisY;
            }
        }
        return max;
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
