package com.letop3.ktsh.view.player;


import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerView {
    private final Player player;
    private final Pane gamePlayer;
    private final ImageView playerImageView;
    private final AnimationHandler animHandler;

    public PlayerView(Player player, Pane gamePlayer) {
        this.player = player;
        this.gamePlayer = gamePlayer;

        animHandler = new AnimationHandler(new PlayerAnimationAdapter(player));

        this.playerImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(playerImageView);

        playerImageView.layoutXProperty().bind(player.getPosition().xProperty());
        playerImageView.layoutYProperty().bind(player.getPosition().yProperty());
    }

    public void update() {
        player.update();
        playerImageView.setImage(animHandler.getFrame());
    }
}
