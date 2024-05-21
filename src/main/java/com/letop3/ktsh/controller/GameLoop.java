package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.GroundView;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLoop implements Initializable {

    private final PlayerView playerView;
    private final GroundView groundView;

    public GameLoop(PlayerView playerView, GroundView groundView) {
        this.playerView = playerView;
        this.groundView = groundView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGameLoop();
    }

    private void startGameLoop() {
        Duration duration = Duration.millis(1000.0 / 30); // 30 FPS
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            updateGame();
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGame() {
        playerView.update();
        groundView.update();
    }
}


