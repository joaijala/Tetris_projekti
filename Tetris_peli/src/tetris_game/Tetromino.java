

package tetris_game;

import java.util.Random;
import java.lang.Math;
/**
 *
 * @author joaijala
 * This class makes the Tetromino pieces
 */
public class Tetromino {
    
    public enum Shape { Empty , Z_shape, S_shape, I_shape, T_shape, O_shape, L_shape, J_shape}
    
    private Shape tetromino_shape; // contains the shape of the tetromino
    private int [][] tetromino_cords; //contains the cordinates of this tetromino (the rotation of the tetromino)
    
    //Table for initialising a new Tetromino
    private final int[][][] cordinate_table=new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };

    
    //The constructor makes a new tetromino whit defalut empty shape
    public Tetromino() {

        this.tetromino_cords = new int[4][2];
        set_tetromino_cords(Shape.Empty);

    }
    
    //Constructor that makes a new tetomino with spesific shape
    public Tetromino(Shape shape){
        this.tetromino_cords = new int[4][2];
        set_tetromino_cords(shape);
    }
    
    //Constructor that make new tetromino using shape number (for testing)
    public Tetromino(int shape){
        this.tetromino_cords = new int[4][2];
        Shape [] shapes=Shape.values();
        set_tetromino_cords(shapes[shape]);
    }
    
    // Sets the cordsof the tetromino according to it's shape
    private void set_tetromino_cords(Shape shape){
        
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                this.tetromino_cords[i][j] = cordinate_table[shape.ordinal()][i][j];
            }
        }
        this.tetromino_shape = shape;
    }
    
    // gets the X value of a part of the shape
    public int get_X (int index){
        return this.tetromino_cords[index][0];
    }
    
    // gets the Y value of a part of the shape
    public int get_Y (int index){
        return this.tetromino_cords[index][1];
    }
    
    // return the shape of the tetromino
    public Shape get_Shape(){
        return this.tetromino_shape;
    }
    
    //sets the X value to the desired part of the shape
    private void set_X(int index, int x){
        tetromino_cords[index][0] = x; 
    }
    
    //sets the Y value to the desired part of the shape
    private void set_Y(int index, int y) {
        tetromino_cords[index][1] = y; 
    }
    //rotates the tetromino left (clockwise) by changing it's cordinartes
    public void rotate_left(){
        if (this.tetromino_shape == Shape.O_shape)
            return;
        
        int help_X;
        
        for (int i=0; i< 4;++i){
            help_X=get_X(i);
            this.set_X(i, get_Y(i));
            this.set_Y(i,-help_X);
        }
    }
    // ratates the tetromino right (counterclockwise) by changing it's cordinates
    public void rotate_right(){
        if (this.tetromino_shape == Shape.O_shape)
            return;
        
        int help_X;
        int help_Y;
        for (int i=0; i<4;++i){
            help_X=get_X(i);
            this.set_X(i, -get_Y(i));
            this.set_Y(i, help_X);
        }
    }

    
}
