package com.letop3.ktsh.view.player;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationHandler;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerView {
    private final ImageView playerImageView;
    private final AnimationHandler animHandler;

    public PlayerView(Player player, Pane gamePlayer) {
        animHandler = new AnimationHandler(new PlayerAnimationAdapter(player));

        this.playerImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(playerImageView);

        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());
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
}
