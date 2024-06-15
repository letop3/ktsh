package com.letop3.ktsh.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainMenuView {

    private MediaPlayer mediaPlayer;

    public void loadNewScene() {
        try {
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/gameGround.fxml")));
            Scene scene = SceneManager.getInstance().getMainScene();
            scene.setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String musicFile) {
        // Créer un objet Media
        Media media = new Media(new File(musicFile).toURI().toString());

        // Initialiser le MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);

        // Configurer la lecture en boucle
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(javafx.util.Duration.ZERO);
            }
        });

        // Jouer la musique
        mediaPlayer.play();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
