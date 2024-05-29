package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.enemy.EnemiesViewsManager;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class GameView {
    private GroundView groundView;
    private PlayerView playerView;
    private EnemiesViewsManager enemiesViewsManager;

    public GameView(Player player, Ground ground, ArrayList<Enemy> enemies, TilePane gameGround, Pane gamePlayer) {
        groundView = new GroundView(ground, gameGround, player);
        playerView = new PlayerView(player, gamePlayer, ground);
        enemiesViewsManager = new EnemiesViewsManager(enemies, gamePlayer, ground);

        gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5- player.getPosition().getX() % Chunk.CHUNK_SIZE);
        gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - player.getPosition().getY() % Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5 - (int)nouv % Chunk.CHUNK_SIZE);
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - (int)nouv % Chunk.CHUNK_SIZE);
        });
    }

    public GroundView getGroundView() {
        return groundView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public EnemiesViewsManager getEnemiesViewsManager() {
        return enemiesViewsManager;
    }

}
