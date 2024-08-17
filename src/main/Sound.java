package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/sound/sonatina_letsadventure_4IslandScenery.wav");
        soundUrl[1] = getClass().getResource("/sound/denied.wav");
        soundUrl[2] = getClass().getResource("/sound/use.wav");
        soundUrl[3] = getClass().getResource("/sound/equip.wav");
        soundUrl[4] = getClass().getResource("/sound/mixkit-bonus-earned-in-video-game-2058.wav");
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {

        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
