package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.player.PlayerView;
import com.letop3.ktsh.view.player.stuff.StuffView;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.Objects;

public class GameView {
    private GroundView groundView;
    private PlayerView playerView;
    private StuffView stuffView;
    private Canvas heartCanvas;
    private Image fullHeart;
    private Image halfHeart;
    private Image emptyHeart;
    private Pane slotPane;

    public GameView(Player player, Ground ground, TilePane gameGround, Pane gamePlayer, Canvas heartCanvas, Pane stuffPane, Pane slotPane) {
        groundView = new GroundView(ground, gameGround, player);
        playerView = new PlayerView(player, gamePlayer);
        stuffView = new StuffView(stuffPane, slotPane, playerView);

        gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5 - player.getPosition().getX() % Chunk.CHUNK_SIZE);
        gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - player.getPosition().getY() % Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5 - (double)nouv % Chunk.CHUNK_SIZE);
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - (double)nouv % Chunk.CHUNK_SIZE);
        });

        this.heartCanvas = heartCanvas;
        this.fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/fullHeart.png")));
        this.halfHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/halfHeart.png")));
        this.emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/emptyHeart.png")));

        stuffView.updateStuff(player.getStuff());
    }

    public GroundView getGroundView() {
        return groundView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public StuffView getStuffView() {
        return stuffView;
    }

    public void updateHpBar(Player player) {
        int fullHearts = player.getHearts()[0];
        int halfHearts = player.getHearts()[1];
        int totalHearts = player.getHearts()[2];

        heartCanvas.getGraphicsContext2D().clearRect(0, 0, heartCanvas.getWidth(), heartCanvas.getHeight());

        for (int i = 0; i < totalHearts; i++) {
            if (i < fullHearts) {
                heartCanvas.getGraphicsContext2D().drawImage(fullHeart, (playerView.getScreenPlayerX().get() - 280) + i * 20, playerView.getScreenPlayerY().get() - 200);
            } else if (i == fullHearts && halfHearts == 1) {
                heartCanvas.getGraphicsContext2D().drawImage(halfHeart, (playerView.getScreenPlayerX().get() - 280) + i * 20, playerView.getScreenPlayerY().get() - 200);
            } else {
                heartCanvas.getGraphicsContext2D().drawImage(emptyHeart, (playerView.getScreenPlayerX().get() - 280) + i * 20, playerView.getScreenPlayerY().get() - 200);
            }
        }
    }
}