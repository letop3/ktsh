package com.letop3.ktsh.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ControlsController implements Initializable {
    private TilePane gameGround;
    private Set<KeyCode> activeKeys;

    public ControlsController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> gameGround.getScene().setOnKeyPressed(e -> activeKeys.add(e.getCode())));
        Platform.runLater(() -> gameGround.getScene().setOnKeyReleased(e -> activeKeys.remove(e.getCode())));
    }

    public Set<KeyCode> getActiveKeys() {
        return activeKeys;
    }

    public void setGameGround(TilePane gameGround) {
        this.gameGround = gameGround;
    }

}
