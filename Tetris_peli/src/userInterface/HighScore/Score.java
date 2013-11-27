/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.HighScore;

import java.io.Serializable;

/**
 * Score on luokka, joka sisältää pisteet ja pisteen temijän nimen.
 *
 * @author Josse
 */
public class Score implements Serializable, Comparable<Score> {

    private final int score;
    private final String name;

    /**
     *
     * @param score pelaajan pisteet
     * @param name pelaajan nimi
     */
    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Vertailee annettua scorea tämän scoren kanssa.
     *
     * @param score1 score johon verrataan
     * @return palauttaa sen minkä compareTo:n kuuluukin palauttaa.
     */
    @Override
    public int compareTo(Score score1) {
        return ((Integer) (score1.getScore())).compareTo(getScore());
    }

}
