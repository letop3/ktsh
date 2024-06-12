package com.letop3.ktsh.view.player;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.entity.player.PlayerListener;
import com.letop3.ktsh.view.animation.AnimationHandler;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerView {
    private final ImageView playerImageView;
    private final AnimationHandler animHandler;
    private Pane gamePlayer;

    public PlayerView(Player player, Pane gamePlayer) {
        animHandler = new AnimationHandler(new PlayerAnimationAdapter(player));
        this.gamePlayer = gamePlayer;

        this.playerImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(playerImageView);

        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());

        player.setPL(new PlayerListener() {
            @Override
            public void onAttack() {
                triggerAttackAnimation(player.getPosition(), player.getLastDirection());
            }
        });
    }

    public void update() {
        playerImageView.setImage(animHandler.getFrame());
    }

    public DoubleProperty getScreenPlayerX() {
        return playerImageView.layoutXProperty();
    }

    public DoubleProperty getScreenPlayerY() {
        return playerImageView.layoutYProperty();
    }

    public void triggerAttackAnimation(Position position, Direction direction) {
        ImageView hitbox = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/hitbox.png"))));
        Timer timer = new Timer();
        switch (direction) {
            case NORTH, NORTH_EAST, NORTH_WEST:
                gamePlayer.getChildren().add(hitbox);
                hitbox.setFitHeight(24);
                hitbox.setFitWidth(48);
                hitbox.setX(getScreenPlayerX().get() - 8);
                hitbox.setY(getScreenPlayerY().get() - 24);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitbox));
                    }
                }, 150);
                break;
            case SOUTH, SOUTH_EAST, SOUTH_WEST:
                gamePlayer.getChildren().add(hitbox);
                hitbox.setFitHeight(24);
                hitbox.setFitWidth(48);
                hitbox.setX(getScreenPlayerX().get() - 8);
                hitbox.setY(getScreenPlayerY().get() + 32);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitbox));
                    }
                }, 150);
                break;
            case EAST:
                gamePlayer.getChildren().add(hitbox);
                hitbox.setFitHeight(48);
                hitbox.setFitWidth(24);
                hitbox.setX(getScreenPlayerX().get() + 30);
                hitbox.setY(getScreenPlayerY().get() - 8);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitbox));
                    }
                }, 150);
                break;
            case WEST:
                gamePlayer.getChildren().add(hitbox);
                hitbox.setFitHeight(48);
                hitbox.setFitWidth(24);
                hitbox.setX(getScreenPlayerX().get() - 20);
                hitbox.setY(getScreenPlayerY().get() - 8);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitbox));
                    }
                }, 150);
                break;
        }
    }
}
