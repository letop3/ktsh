package com.letop3.ktsh.view;

import com.letop3.ktsh.model.Player;
import com.letop3.ktsh.utils.TilesetCutter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerView {

    Player player;
    Pane gamePlayer;
    ImageView playerImageView;
    TilesetCutter cutter;

    public PlayerView(Player player, Pane gamePlayer) {
        this.player = player;
        this.gamePlayer = gamePlayer;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/player/player.png", 32);

        Image playerImage = cutter.getTile(1);
        this.playerImageView = new ImageView(playerImage);


    }

    public void draw() {
        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());
        if (!gamePlayer.getChildren().contains(playerImageView)) {
            gamePlayer.getChildren().add(playerImageView);
        }
    }

    public void update() {
        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());
    }

}