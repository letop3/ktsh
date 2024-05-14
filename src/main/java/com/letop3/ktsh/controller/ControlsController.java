package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class ControlsController implements Initializable {
    public static ArrayList<Direction> keyDirections = new ArrayList<>();
    private final TilePane gameGround;
    private static Player player;

    public ControlsController(TilePane gameGround, Player player) {
        this.gameGround = gameGround;
        ControlsController.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            gameGround.getScene().setOnKeyPressed(this::keyPressed);
            gameGround.getScene().setOnKeyReleased(this::keyReleased);
        });
    }

    private Direction keyToDirection(KeyCode key) {
        if (key == KeyPreference.MOVE_UP)
            return Direction.NORTH;
        else if (key == KeyPreference.MOVE_DOWN)
            return Direction.SOUTH;
        else if (key == KeyPreference.MOVE_RIGHT)
            return Direction.EAST;
        else if (key == KeyPreference.MOVE_LEFT)
            return Direction.WEST;

        return null;
    }

    private void keyPressed(KeyEvent event) {
        Direction dir = keyToDirection(event.getCode());
        if (dir != null && !keyDirections.contains(dir)) {
            keyDirections.add(dir);
        }
        player.move(keyDirections);
    }

    private void keyReleased(KeyEvent event) {
        keyDirections.remove(keyToDirection(event.getCode()));
        player.move(keyDirections);
    }

}
