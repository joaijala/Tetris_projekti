/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.HighScore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tämä luokka hallitsee highscorea.
 *
 * @author Josse
 */
public final class HighScoreManager {

    /**
     * ArrayLista, joka sisältää pelin high scoret.
     */
    private ArrayList<Score> scores;
    /**
     * Tiedosto, mihin pisteet tallennetaan.
     */
    private static final String HIGHSCORE_FILE = "scores.dat";
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;

    /**
     * Luo scores arrayListin ja lataa siihen Highscoretiedoston sisällön.
     */
    public HighScoreManager() {

        scores = new ArrayList<>(10);
        try {
            loadScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Järjestää scores listan.
     */
    private void sort() {
        Collections.sort(scores);

    }

    /**
     * Lisää uuden scoren scores taulukkoon.
     *
     * @param name scoren nimi
     * @param score scoren pistemäärä
     */
    public void addScore(String name, int score) {
        if (scores.size() > 9) {
            scores.remove(9);
        }
        scores.add(new Score(score, name));
        sort();
        try {
            updateScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Poistaa halutun pisteen. Tämän avulla testin ajaminen ei vaikuta highscoreen.
     * @param name poistettavan pisteen nimi
     */
    public void removeScore(String name){
        Score score=new Score(0,null);
        for(int i=0;i<scores.size()-1;i++){
            if(scores.get(i).getName().equals(name)){
                score=scores.get(i);
            }
        }
        if(score.getName()!=null){
          scores.remove(score);
        }
        try {
            updateScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Tyhjentää scores taulukon ja tiedoston.
     */
    public void clearScores() {
        scores.removeAll(scores);
        try {
            updateScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lataa pisteet tiedostosta.
     *
     * @throws IOException
     */
    private void loadScoreFile() throws IOException {
        File file = new File(HIGHSCORE_FILE);
        if (!file.isFile()) {
            if (!file.createNewFile()) {

                throw new IOException("Error creating new file: " + file.getAbsolutePath());

            }
        }
        else {

            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                scores = (ArrayList<Score>) inputStream.readObject();
            }
            catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
            catch (ClassNotFoundException e) {
                System.out.println("[Laad] CNF Error: " + e.getMessage());
            }
        }
    }

    /**
     * Kirjoittaa pisteet tiedostoon.
     *
     * @throws IOException
     */
    private void updateScoreFile() throws IOException {
        File file = new File(HIGHSCORE_FILE);
        if (!file.isFile() && !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(scores);
        }
        catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            }
            catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    /**
     * Palauttaa kaikki highScoret yhtenä stringinä
     *
     * @return sores taulukon string esitys.
     */
    @Override
    public String toString() {
        String highscoreString = "";
        ArrayList<Score> printedScores;
        printedScores = getScores();
        int i = 0;
        int x = printedScores.size();

        while (i < x) {
            highscoreString += (i + 1) + ".\t" + printedScores.get(i).getName() + "\t\t" + printedScores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }

    /**
     * Palauttaa highsscoren pienimmän pisteen. Jos highscoressa ei ole 10
     * scorea palautetaan 0.
     *
     * @return Pienin piste.
     */
    public int getSmallestScore() {
        if (scores.size() < 10) {
            return 0;
        }
        else {
            return scores.get(9).getScore();
        }
    }

    /**
     * Palauttaa scores listan.
     *
     * @return scores
     */
    public ArrayList<Score> getScores() {
        return scores;
    }

    /**
     * Palauttaa tietyllä sijalla ollut score.
     *
     * @param i monesko listan pisteistä palautetaan.
     * @return halutun rivin score
     */
    public Score getScore(int i) {
        if (scores.size() < i - 1) {
            return null;
        }
        return scores.get(i);
    }

}
