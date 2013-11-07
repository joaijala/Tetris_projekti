/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisGame;

/**
 *
 * @author Järjestelmänvalvoja
 * Tämä luokka hoitaa pelilaudan toteutuksen. Pelilaudall on kaikki jo tippuneet tetromiinot
 */
public class Board {
    
    private int [][] board;
    
    public Board(){
        this.board= new int [20][10];
        setAllToZero();
    }
    
    //nolla koko talukon, niin että siinä on Empty tetromiinoja eli 0
    public void setAllToZero(){
        for (int i=0; i<20;i++){
            for(int j=0; j<10;j++){
                this.board[i][j]=0;
            }
        }
    }
    
    //palauttaa boardin tilanteen (eli board taulukon)
    public int[][] getBoardStatus(){
        return this.board;
    }
    
    //asettaa boardin ruutuun halutun arvon 
    public void setNumberToBoard(int x, int y, int number){
        this.board[y][x]=number;
    }
}
