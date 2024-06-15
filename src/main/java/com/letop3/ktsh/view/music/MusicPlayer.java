package com.letop3.ktsh.view.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class MusicPlayer {

    private MediaPlayer backgroundPlayer;

    public void playBackgroundMusic(String musicFile) {
        String path = Paths.get(musicFile).toUri().toString();
        Media media = new Media(path);
        backgroundPlayer = new MediaPlayer(media);
        backgroundPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                backgroundPlayer.seek(Duration.ZERO);
            }
        });
        backgroundPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
        }
    }

    public void playSound(String soundFile) {
        String path = Paths.get(soundFile).toUri().toString();
        Media sound = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
