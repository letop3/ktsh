package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.artefact.Artefact;
import com.letop3.ktsh.model.item.consomable.Consomable;
import com.letop3.ktsh.view.GameView;
import com.letop3.ktsh.view.StuffClickListener;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Initializable {

    private Env env;
    private GameView view;

    @FXML
    public Pane stuffPane;
    @FXML
    private Canvas heartCanvas;
    @FXML
    private TilePane gameGround;

    @FXML
    private Pane gamePlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Env();

        GameView gameView = new GameView(env.getPlayer(), env.getGround(), gameGround, gamePlayer, heartCanvas, stuffPane);
        this.view = gameView;

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(env, gameGround, gameView.getPlayerView());
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer(), gameView.getStuffView());
        StuffController stuffController = new StuffController(view, env);

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);
        stuffController.initialize(location, resources);
    }
}
