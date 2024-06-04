package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLoop implements Initializable {
    private final Env env;
    private final PlayerView playerView;

    public GameLoop(Env env, TilePane gameGround, PlayerView playerView) {
        this.env = env;
        this.playerView = playerView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGameLoop();
    }

    private void startGameLoop() {
        Duration duration = Duration.millis(1000.0 / 30); // 30 FPS
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            env.update();
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


