package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.BlockM;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.entity.EntityAnimationAdapter;
import com.letop3.ktsh.view.entity.EntityView;
import com.letop3.ktsh.view.entity.MobView;
import com.letop3.ktsh.view.entity.NPC.BlockMView;
import com.letop3.ktsh.view.entity.NPC.DialogueView;
import com.letop3.ktsh.view.entity.NPC.NPCView;
import com.letop3.ktsh.view.player.PlayerView;
import com.letop3.ktsh.view.player.stuff.StuffView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameView {
    private final Player player;
    private final GroundView groundView;
    private final PlayerView playerView;
    private final StuffView stuffView;
    private final DialogueView dialogueView;
    private final HBox heartBox;
    private final Image fullHeart;
    private final Image halfHeart;
    private final Image emptyHeart;
    private final ItemView itemView;
    private final Pane entityPane;

    private final Position screenPosition;

    private final Map<Entity, EntityView> entities;

    public GameView(Player player,
                    Ground ground,
                    TilePane gameGround,
                    Pane gamePlayer,
                    HBox heartBox,
                    Pane stuffPane,
                    Pane[] slotPane,
                    Pane entityPane,
                    GridPane dialogueBox,
                    Label dialogueText,
                    ListView<String> dialogueResponses, Pane itemEffectPane) {

        screenPosition = new Position(Chunk.CHUNK_SIZE * 1.5 - player.getPosition().getX(), Chunk.CHUNK_SIZE * 1.5 - player.getPosition().getY());

        groundView = new GroundView(ground, gameGround);
        playerView = new PlayerView(player, gamePlayer, this);
        stuffView = new StuffView(stuffPane, slotPane, playerView);
        itemView = new ItemView(playerView, itemEffectPane, gamePlayer, screenPosition);
        dialogueView = new DialogueView(dialogueBox, dialogueText, dialogueResponses);

        this.entityPane = entityPane;
        this.player = player;

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

        // Entity views creation
        this.entities = new HashMap<>();
        for (Chunk[] chunks : ground.getChunks()) {
            for (Chunk chunk : chunks) {
                for (Entity entity : chunk.getEntities()) {
                    createEntityView(entity);
                }
            }
        }

        player.getEnv().setListener((entity) -> {
            createEntityView(entity);
        });

        this.heartBox = heartBox;
        this.fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/fullHeart.png")));
        this.halfHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/halfHeart.png")));
        this.emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/emptyHeart.png")));

        stuffView.updateStuff(player.getStuff());
    }

    private void createEntityView(Entity entity) {
        Pane entityImageView = new Pane();
        entityPane.getChildren().add(entityImageView);

        EntityView entityView = null;
        if (entity instanceof NPC) {
            entityView = new NPCView(new EntityAnimationAdapter(entity), entityImageView, screenPosition, (NPC)entity, player, dialogueView);
        }
        else if (entity instanceof Enemy) {
            entityView = new MobView(new EntityAnimationAdapter(entity), entityImageView, screenPosition);
        }
         else if (entity instanceof BlockM) {
            entityView = new BlockMView(entity, entityImageView, screenPosition);
        }

        if (entityView != null) entities.putIfAbsent(entity, entityView);
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

    public ItemView getItemView(){
        return itemView;
    }

    public void update() {
        int fullHearts = player.getHearts()[0];
        int halfHearts = player.getHearts()[1];
        int totalHearts = player.getHearts()[2];

        heartBox.getChildren().clear();

        for (int i = 0; i < totalHearts; i++) {
            if (i < fullHearts) {
                ImageView fullHeartView = new ImageView(fullHeart);
                //fullHeartView.setFitHeight(25);  // Ajustez la taille selon vos besoins
                //fullHeartView.setFitWidth(25);   // Ajustez la taille selon vos besoins
                heartBox.getChildren().add(fullHeartView);
            } else if (i == fullHearts && halfHearts == 1) {
                ImageView halfHeartView = new ImageView(halfHeart);
                //halfHeartView.setFitHeight(25);  // Ajustez la taille selon vos besoins
                //halfHeartView.setFitWidth(25);   // Ajustez la taille selon vos besoins
                heartBox.getChildren().add(halfHeartView);
            } else {
                ImageView emptyHeartView = new ImageView(emptyHeart);
                //emptyHeartView.setFitHeight(25); // Ajustez la taille selon vos besoins
                //emptyHeartView.setFitWidth(25);  // Ajustez la taille selon vos besoins
                heartBox.getChildren().add(emptyHeartView);
            }
        }

        playerView.update();

        for (Chunk chunk : groundView.getGround().getCurrentChunks()) {
            for (Entity entity : chunk.getEntities()) {
                EntityView entityView = entities.get(entity);
                if (entityView != null) entityView.update();
            }
        }
    }

    public Map<Entity, EntityView> getEntities() {
        return entities;
    }
}