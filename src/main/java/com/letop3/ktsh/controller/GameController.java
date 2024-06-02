package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.view.GameView;
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
        GameLoop gameLoopController = new GameLoop(gameGround, gameView.getPlayerView(), gameView.getGroundView());
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer());

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);

        // ajout listener pour update hb bar
        env.getPlayer().hpProperty().addListener((observable, oldValue, newValue) -> {
            view.updateHpBar(env.getPlayer());
        });

        view.updateHpBar(env.getPlayer());

        // Test perte hp pour update bar hp
        Timer timer = new Timer();
        for (int i = 1; i <= 10; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("-1 hp");
                    env.getPlayer().setHp(env.getPlayer().getHp() - 1);
                }
            }, i * 5000);
        }

        // ajouter listener sur changement stuff
        env.getPlayer().getStuff().getInventaire().addListener(new ListChangeListener<Item>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Item> change) {
                Platform.runLater(() -> view.updateStuff(env.getPlayer().getStuff())); //Platform.runLater fait tourner sur le meme thread de l'app javafx
            }
        });

        view.updateStuff(env.getPlayer().getStuff());

        // Test changement dans le stuff
        Timer timerStuff = new Timer();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            timerStuff.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("-1 item");
                    env.getPlayer().getStuff().getInventaire().remove(finalI);
                }
            }, i * 5000);
        }

    }
}