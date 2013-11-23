/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.HighScore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Koodikatselmoijalle, tämä on täysin työn alla joten se on hiukan sekasin
 *
 * @author Johanna
 */
public class HighScoreManager {

    private ArrayList<Score> scores;
    private static final String HIGHSCORE_FILE = "scores.dat";

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager() {

        scores = new ArrayList<Score>(10);
        try {
            loadScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sort() {
        Collections.sort(scores);

    }

    public int getSmallestScore() {
        if(scores.size()<10){
            return 0;
        }
        else{
            return scores.get(9).getScore();
        }
    }

    public ArrayList<Score> getScores() {
        return scores;
    }
    public Score getScore(int i){
        if(scores.size()<i-1){
            return null;
        }
        return scores.get(i);
    }

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
    public void clearScores(){
        scores.removeAll(scores);
        try {
            updateScoreFile();
        }
        catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadScoreFile() throws IOException {
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

    public void updateScoreFile() throws IOException {
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

    public String getHighscoreString() {
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

}
