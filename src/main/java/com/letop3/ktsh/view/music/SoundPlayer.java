package com.letop3.ktsh.view.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SoundPlayer {

    private static MediaPlayer backgroundPlayer;

    public static void playBackgroundMusic(String musicFile) {
        if (Files.notExists(Paths.get(musicFile))) {
            System.err.println("Le fichier de musique n'existe pas : " + musicFile);
            return;
        }

        try {
            String path = Paths.get(musicFile).toUri().toString();
            Media media = new Media(path);
            backgroundPlayer = new MediaPlayer(media);
            backgroundPlayer.setOnEndOfMedia(() -> backgroundPlayer.seek(Duration.ZERO));
            backgroundPlayer.play();
        } catch (MediaException e) {
            System.err.println("Erreur lors de la lecture de la musique : " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
        }
    }

    public static void changeBackgroundMusic(String newMusicFile) {
        if (backgroundPlayer != null) {
            stopBackgroundMusic();
        }
        playBackgroundMusic(newMusicFile);
    }

    public static void playSound(String soundFile) {
        if (Files.notExists(Paths.get(soundFile))) {
            System.err.println("Le fichier de son n'existe pas : " + soundFile);
            return;
        }

        try {
            String path = Paths.get(soundFile).toUri().toString();
            Media sound = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (MediaException e) {
            System.err.println("Erreur lors de la lecture du son : " + e.getMessage());
        }
    }
}
