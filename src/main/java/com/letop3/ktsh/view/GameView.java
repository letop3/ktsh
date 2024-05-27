package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class GameView {
    private GroundView groundView;
    private PlayerView playerView;

    public GameView(Player player, Ground ground, TilePane gameGround, Pane gamePlayer) {
        groundView = new GroundView(ground, gameGround, player);
        playerView = new PlayerView(player, gamePlayer, ground);

        gameGround.setTranslateX(Chunk.CHUNK_SIZE - player.getPosition().getX() % Chunk.CHUNK_SIZE);
        gameGround.setTranslateY(Chunk.CHUNK_SIZE - player.getPosition().getY() % Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateX(Chunk.CHUNK_SIZE - (int)nouv % Chunk.CHUNK_SIZE);
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateY(Chunk.CHUNK_SIZE - (int)nouv % Chunk.CHUNK_SIZE);
        });
    }

    public GroundView getGroundView() {
        return groundView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
}
