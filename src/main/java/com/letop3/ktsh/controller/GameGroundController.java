package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GameView;
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

        GameView gameView = new GameView(env.getPlayer(), env.getGround(), gameGround, gamePlayer);

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(gameGround, gameView.getPlayerView(), gameView.getGroundView());
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer());

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);
    }
}