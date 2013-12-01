/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sounds;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Hoitaa pelimusiikin soittamisen.
 * @author Josse
 */
public class GameMusic {

    private final String musicName;
    private Clip clip;

    /**
     *
     * @param name käytetyn musiikin nimi
     */
    public GameMusic(String name) {
        this.musicName = name;
    }

    /**
     * Käynnistää musiikin soittamisen.
     */
    public void playMusic() {
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicName + ".wav").getAbsoluteFile());
            if(System.getProperty("os.name").contains("Linux")){
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                clip = (Clip)AudioSystem.getLine(info);
            }
            else{
                clip = AudioSystem.getClip();
            }
            clip.open(audioInputStream);
            clip.start();
            clip.loop(100);
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    /**
     * Lopettaa musiikin soittamisen.
     */
    public void stopMusic(){
        if (clip==null){
            return;
        }
        clip.stop();
    }
    
    /**
     * Kertoo onko musiikin soitto käynnissä.
     * @return onko musiikki soittamssa
     */
    public boolean isPlaying(){
        if (clip==null){
            return false;
        }
        return clip.isRunning();
    }

}
