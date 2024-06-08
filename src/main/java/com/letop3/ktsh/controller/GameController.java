package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.GameView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private Env env;
    private GameView view;

    @FXML
    public Pane slotPane;
    @FXML
    public Pane entityPane;
    @FXML
    public Pane stuffPane;
    @FXML
    private Canvas heartCanvas;
    @FXML
    private TilePane gameGround;
    @FXML
    private Pane gamePlayer;
    @FXML
    private GridPane dialogueBox;
    @FXML
    private Label dialogueText;
    @FXML
    private ListView<String> dialogueResponses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Env();

        GameView gameView = new GameView(
                env.getPlayer(),
                env.getGround(),
                gameGround,
                gamePlayer,
                heartCanvas,
                stuffPane,
                slotPane,
                entityPane,
                dialogueBox,
                dialogueText,
                dialogueResponses);
        this.view = gameView;

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(env, gameGround, gameView);
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer(), gameView.getStuffView());
        StuffController stuffController = new StuffController(view, env);

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);
        stuffController.initialize(location, resources);
    }
}
