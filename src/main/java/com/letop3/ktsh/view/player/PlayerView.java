package com.letop3.ktsh.view.player;

import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationHandler;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PlayerView {
    private final Ground ground;
    private final Player player;
    private final ImageView playerImageView;
    private final AnimationHandler animHandler;
    private ImageView heart;

    public PlayerView(Player player, Pane gamePlayer, Ground ground) {
        this.ground = ground;
        this.player = player;

        animHandler = new AnimationHandler(new PlayerAnimationAdapter(player));

        this.playerImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(playerImageView);

        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());
    }

    public void update() {
        player.update(ground);
        playerImageView.setImage(animHandler.getFrame());
    }

    public DoubleProperty getScreenPlayerX() {
        return playerImageView.layoutXProperty();
    }

    public DoubleProperty getScreenPlayerY() {
        return playerImageView.layoutYProperty();
    }
}
