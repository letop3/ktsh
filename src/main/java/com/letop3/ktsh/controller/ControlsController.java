package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.utils.preferences.GamePreferences;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import com.letop3.ktsh.view.player.stuff.StuffView;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController implements Initializable {
    private final TilePane gameGround;
    private static Player player;
    private final StuffView stuffView;
    private final Env env;

    public ControlsController(TilePane gameGround, Player player, StuffView stuffView, Env env) {
        this.gameGround = gameGround;
        ControlsController.player = player;
        this.stuffView = stuffView;
        this.env = env;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameGround.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::keyPressed);
                newScene.setOnKeyReleased(this::keyReleased);
            }
        });
    }

    private Direction keyToDirection(KeyCode key) {
        if (key == GamePreferences.getKeyCodePreference(KeyPreference.MOVE_UP.getKey(), KeyPreference.MOVE_UP.getDefaultValue()))
            return Direction.NORTH;
        else if (key == GamePreferences.getKeyCodePreference(KeyPreference.MOVE_DOWN.getKey(), KeyPreference.MOVE_DOWN.getDefaultValue()))
            return Direction.SOUTH;
        else if (key == GamePreferences.getKeyCodePreference(KeyPreference.MOVE_RIGHT.getKey(), KeyPreference.MOVE_RIGHT.getDefaultValue()))
            return Direction.EAST;
        else if (key == GamePreferences.getKeyCodePreference(KeyPreference.MOVE_LEFT.getKey(), KeyPreference.MOVE_LEFT.getDefaultValue()))
            return Direction.WEST;

        return null;
    }

    private void keyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == GamePreferences.getKeyCodePreference(KeyPreference.INTERACT.getKey(), KeyPreference.INTERACT.getDefaultValue())) {
            player.interract();
        } else if (key == GamePreferences.getKeyCodePreference(KeyPreference.INVENTORY.getKey(), KeyPreference.INVENTORY.getDefaultValue())) {
            stuffView.toogleVisibility();
        } else if (key == GamePreferences.getKeyCodePreference(KeyPreference.QUICK_SLOT.getKey(), KeyPreference.QUICK_SLOT.getDefaultValue())) {
            player.useQuickSlot();
        } else if (key == GamePreferences.getKeyCodePreference(KeyPreference.ATTACK.getKey(), KeyPreference.ATTACK.getDefaultValue())) {
            player.attack(env);
        } else {
            player.addDirection(keyToDirection(event.getCode()));
        }
    }

    private void keyReleased(KeyEvent event) {
        player.remDirection(keyToDirection(event.getCode()));
    }
}
