package com.letop3.ktsh.controller;

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

    public GameLoop(TilePane gameGround) {
        this.gameGround = gameGround;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGameLoop();
    }

    private void startGameLoop() {
        Duration duration = Duration.millis(1000.0 / 144); // 144 FPS
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            updateGame();
            drawGame();
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void updateGame() {
        // This method can be expanded to update the game state as needed.
    }

    private void drawGame() {
        // This method can be expanded to draw the game state as needed.
    }
}


