package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import com.letop3.ktsh.view.player.stuff.StuffView;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController implements Initializable {
    private final TilePane gameGround;
    private static Player player;
    private StuffView stuffView;

    public ControlsController(TilePane gameGround, Player player, StuffView stuffView) {
        this.gameGround = gameGround;
        ControlsController.player = player;
        this.stuffView = stuffView;
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
        KeyCode key = event.getCode();
		if (key == KeyPreference.INTERACT)
			player.interract();
        else if (key == KeyPreference.INVENTORY)
            stuffView.toogleVisibility();
        else if (key == KeyPreference.QUICK_SLOT)
            player.useQuickSlot();
        else player.addDirection(keyToDirection(event.getCode()));
    }

    private void keyReleased(KeyEvent event) {
        player.remDirection(keyToDirection(event.getCode()));
    }
}
