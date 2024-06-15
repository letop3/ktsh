package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLoop implements Initializable {
    private final Env env;
    private final GameView gameView;

    public GameLoop(Env env, TilePane gameGround, GameView gameView) {
        this.env = env;
        this.gameView = gameView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGameLoop();
    }

    private void startGameLoop() {
        Duration duration = Duration.millis(1000.0 / 30); // 30 FPS
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            env.update();
            gameView.update();
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
