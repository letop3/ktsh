package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GroundView;
import com.letop3.ktsh.view.PlayerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameGroundController implements Initializable {

    private Env env;

    @FXML
    private TilePane gameGround;

    @FXML
    private Pane gamePlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Env();

        GameLoop gameLoopController = new GameLoop(gameGround);
        ControlsController controlsController = new ControlsController();
        GroundView groundView = new GroundView(gameGround);
        PlayerView playerView = new PlayerView(env.getPlayer(), gamePlayer);

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);

        groundView.draw();
        playerView.draw();

    }
}