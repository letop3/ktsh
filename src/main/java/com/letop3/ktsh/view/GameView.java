package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Mob;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.entity.EntityAnimationAdapter;
import com.letop3.ktsh.view.entity.EntityView;
import com.letop3.ktsh.view.entity.MobView;
import com.letop3.ktsh.view.entity.NPC.DialogueView;
import com.letop3.ktsh.view.entity.NPC.NPCView;
import com.letop3.ktsh.view.player.PlayerView;
import com.letop3.ktsh.view.player.stuff.StuffView;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameView {
    private Player player;
    private GroundView groundView;
    private PlayerView playerView;
    private StuffView stuffView;
    private Canvas heartCanvas;
    private Image fullHeart;
    private Image halfHeart;
    private Image emptyHeart;

    private Position screenPosition;

    private Map<Entity, EntityView> entities;

    public GameView(Player player,
                    Ground ground,
                    TilePane gameGround,
                    Pane gamePlayer,
                    Canvas heartCanvas,
                    Pane stuffPane,
                    Pane slotPane,
                    Pane entityPane,
                    GridPane dialogueBox,
                    Label dialogueText,
                    ListView<String> dialogueResponses) {
        groundView = new GroundView(ground, gameGround, player);
        playerView = new PlayerView(player, gamePlayer);
        stuffView = new StuffView(stuffPane, slotPane, playerView);
        DialogueView dialogueView = new DialogueView(dialogueBox, dialogueText, dialogueResponses);

        this.player = player;

        screenPosition = new Position(Chunk.CHUNK_SIZE * 1.5 - player.getPosition().getX(), Chunk.CHUNK_SIZE * 1.5 - player.getPosition().getY());

        gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5 - player.getPosition().getX() % Chunk.CHUNK_SIZE);
        gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - player.getPosition().getY() % Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateX(Chunk.CHUNK_SIZE * 0.5 - (double)nouv % Chunk.CHUNK_SIZE);
            screenPosition.setX(Chunk.CHUNK_SIZE * 1.5 - (double)nouv);
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            gameGround.setTranslateY(Chunk.CHUNK_SIZE * 0.5 - (double)nouv % Chunk.CHUNK_SIZE);
            screenPosition.setY(Chunk.CHUNK_SIZE * 1.5 - (double)nouv);
        });

        this.entities = new HashMap<>();
        for (Chunk chunks[] : ground.getChunks()) {
            for (Chunk chunk : chunks) {
                for (Entity entity : chunk.getEntities()) {
                    Pane entityImageView = new Pane();
                    entityPane.getChildren().add(entityImageView);

                    EntityView entityView = null;
                    if (entity instanceof NPC) {
                        entityView = new NPCView(new EntityAnimationAdapter(entity), entityImageView, screenPosition, (NPC)entity, player, dialogueView);
                    }
					else if (entity instanceof Mob) {
						entityView = new MobView(new EntityAnimationAdapter(entity), entityImageView, screenPosition);
					}

                    if (entityView != null) entities.putIfAbsent(entity, entityView);
                }
            }
        }

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

    public void update() {
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

        playerView.update();

        for (Entity entity : groundView.getGround().getCurrentChunk().getEntities()) {
            entities.get(entity).update();
        }
        for (Chunk chunk : groundView.getGround().getCurrentChunk().getNeighbors()) {
            for (Entity entity : chunk.getEntities()) {
                entities.get(entity).update();
            }
        }
    }
}