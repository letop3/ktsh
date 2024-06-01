package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GameView;
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
    private Canvas heartCanvas;
    @FXML
    private TilePane gameGround;

    @FXML
    private Pane gamePlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Env();

        GameView gameView = new GameView(env.getPlayer(), env.getGround(), gameGround, gamePlayer, heartCanvas);
        this.view = gameView;

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(gameGround, gameView.getPlayerView(), gameView.getGroundView());
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer());

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);

        view.updateHpDisplay(env.getPlayer());

        // Test perte hp pour update bar hp
        Timer timer = new Timer();
        for (int i = 1; i < 5; i++) {
            int finalI = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(finalI *10 + "secondes");

                    env.getPlayer().setHp(env.getPlayer().getHp() - 1);
                    view.updateHpDisplay(env.getPlayer());
                }
            }, i * 10000);
        }
    }
}