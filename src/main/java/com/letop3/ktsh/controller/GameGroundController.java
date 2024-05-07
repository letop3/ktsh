package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Ground;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class GameGroundController implements Initializable {

    private final Image I1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/tiles/1.png")));
    private final Image I2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/tiles/2.png")));
    private final Image I3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/tiles/3.png")));
    private Ground ground;
    private Set<KeyCode> activeKeys;

    @FXML
    private TilePane gameGround;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ground = new Ground();
        this.activeKeys = new HashSet<>();
        this.drawGround();
        this.drawPlayer();
        this.startGameLoop();

        Platform.runLater(() -> gameGround.getScene().setOnKeyPressed(e -> activeKeys.add(e.getCode())));
        Platform.runLater(() -> gameGround.getScene().setOnKeyReleased(e -> activeKeys.remove(e.getCode())));
    }

    void drawGround() {
        int[] groundData = ground.getGROUND();
        gameGround.getChildren().clear();
        for (int i : groundData) {
            ImageView imageView = new ImageView();
            switch (i) {
                case 1 -> imageView.setImage(I1);
                case 2 -> imageView.setImage(I2);
                case 3 -> imageView.setImage(I3);
                default -> imageView.setImage(I1);
            }
            StackPane tileStack = new StackPane(imageView);
            gameGround.getChildren().add(tileStack);
        }
    }

    private void drawPlayer() {
        Image playerSprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/1.png")));
        ImageView playerView = new ImageView(playerSprite);

        int playerX = ground.getPlayer().getPosition().getX();
        int playerY = ground.getPlayer().getPosition().getY();
        int playerIndex = playerY * 11 + playerX;

        if (gameGround.getChildren().size() > playerIndex) {
            StackPane tileStack = (StackPane) gameGround.getChildren().get(playerIndex);
            tileStack.getChildren().add(playerView);
        }
    }

    private void startGameLoop() {
        Duration duration = Duration.millis(1000.0 / 144); // 144 FPS
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            updateGame();
            drawGame();
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGame() {
        if (activeKeys.contains(KeyCode.UP) && activeKeys.contains(KeyCode.LEFT)) {
            System.out.println("UP-LEFT");
        } else if (activeKeys.contains(KeyCode.UP) && activeKeys.contains(KeyCode.RIGHT)) {
            System.out.println("UP-RIGHT");
        } else if (activeKeys.contains(KeyCode.DOWN) && activeKeys.contains(KeyCode.LEFT)) {
            System.out.println("DOWN-LEFT");
        } else if (activeKeys.contains(KeyCode.DOWN) && activeKeys.contains(KeyCode.RIGHT)) {
            System.out.println("DOWN-RIGHT");
        } else if (activeKeys.contains(KeyCode.UP)) {
            System.out.println("UP");
        } else if (activeKeys.contains(KeyCode.DOWN)) {
            System.out.println("DOWN");
        } else if (activeKeys.contains(KeyCode.LEFT)) {
            System.out.println("LEFT");
        } else if (activeKeys.contains(KeyCode.RIGHT)) {
            System.out.println("RIGHT");
        }
    }

    private void drawGame() {
        // This method can be expanded to redraw the entire game scene as needed.
    }
}
