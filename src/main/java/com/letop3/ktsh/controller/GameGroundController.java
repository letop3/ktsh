package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GroundView;
import com.letop3.ktsh.view.player.PlayerView;
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

        //on s'occupe des vues
        GroundView groundView = new GroundView(env.getGround(), gameGround);
        PlayerView playerView = new PlayerView(env.getPlayer(), gamePlayer, env.getGround());

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(gameGround, playerView, groundView);
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer());

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);
    }
}