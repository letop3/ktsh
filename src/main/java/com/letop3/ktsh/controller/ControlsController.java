package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Player;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ControlsController implements Initializable {
    private final TilePane gameGround;
    public static Set<KeyCode> pressedKeys = new HashSet<>();
    private static Player player;

    public ControlsController(TilePane gameGround, Player player) {
        this.gameGround = gameGround;
        ControlsController.player = player;
    }

    public static void processInput() {
        if (pressedKeys.contains(KeyCode.UP) && pressedKeys.contains(KeyCode.LEFT)) {
            player.moveTopLeft();
        } else if (pressedKeys.contains(KeyCode.UP) && pressedKeys.contains(KeyCode.RIGHT)) {
            player.moveTopRight();
        } else if (pressedKeys.contains(KeyCode.DOWN) && pressedKeys.contains(KeyCode.LEFT)) {
            player.moveBottomLeft();
        } else if (pressedKeys.contains(KeyCode.DOWN) && pressedKeys.contains(KeyCode.RIGHT)) {
            player.moveBottomRight();
        } else if (pressedKeys.contains(KeyCode.LEFT)) {
            player.moveLeft();
        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
            player.moveRight();
        } else if (pressedKeys.contains(KeyCode.UP)) {
            player.moveUp();
        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            player.moveDown();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            gameGround.getScene().setOnKeyPressed(this::keyPressed);
            gameGround.getScene().setOnKeyReleased(this::keyReleased);
        });
    }

    private void keyPressed(KeyEvent event) {
        pressedKeys.add(event.getCode());
    }

    private void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

}
