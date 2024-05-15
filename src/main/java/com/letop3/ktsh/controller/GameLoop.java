package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.PlayerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class GameLoop implements Initializable {
    private Set<KeyCode> activeKeys;

    private final TilePane gameGround;
    private final PlayerView playerView;

    public GameLoop(TilePane gameGround, PlayerView playerView) {
        this.gameGround = gameGround;
        this.playerView = playerView;
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
    }
}


