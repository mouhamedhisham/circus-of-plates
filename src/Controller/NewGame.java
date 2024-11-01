/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Circus;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author elsam
 */
public class NewGame extends GameState{
    public NewGame(Circus c) {
        super(c);
    }

    @Override
    public void gameAction() {
        try {
            File soundFile = new File("Assets\\game-start.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // Wait for the sound to finish playing
            while (!clip.isRunning()) {
                Thread.sleep(1);
            }
            while (clip.isRunning()) {
                Thread.sleep(1);
            }

            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean getState(){
        return true;
    }
    
    
}
