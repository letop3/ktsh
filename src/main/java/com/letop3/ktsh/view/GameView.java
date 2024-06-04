package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.entity.EntityAnimationAdapter;
import com.letop3.ktsh.view.entity.EntityView;
import com.letop3.ktsh.view.entity.NPCView;
import com.letop3.ktsh.view.player.PlayerView;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameView {
    private GroundView groundView;
    private PlayerView playerView;
    private Canvas heartCanvas;
    private Image fullHeart;
    private Image halfHeart;
    private Image emptyHeart;
    private Pane stuffPane;
    private StuffClickListener stuffClickListener;

    private Position screenPosition;

    private Map<Entity, EntityView> entities;

    public GameView(Player player, Ground ground, TilePane gameGround, Pane gamePlayer, Canvas heartCanvas, Pane stuffPane, Pane entityPane) {
        groundView = new GroundView(ground, gameGround, player);
        playerView = new PlayerView(player, gamePlayer);

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
                        entityView = new NPCView(new EntityAnimationAdapter(entity), entityImageView, screenPosition, (NPC)entity, player);
                    }

                    if (entityView != null) entities.putIfAbsent(entity, entityView);
                }
            }
        }

        this.heartCanvas = heartCanvas;
        this.fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/fullHeart.png")));
        this.halfHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/halfHeart.png")));
        this.emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/emptyHeart.png")));

        // Initialize stuffPane
        this.stuffPane = stuffPane;
        drawStuff(player.getStuff());
    }

    public GroundView getGroundView() {
        return groundView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void setStuffClickListener(StuffClickListener stuffClickListener) {
        this.stuffClickListener = stuffClickListener;
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

    private void drawStuff(Stuff stuff) {
        stuffPane.getChildren().clear();

        Image mainGIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getMainG() == null ? "/com/letop3/ktsh/images/item/mainGView.png" : stuff.getMainG().getIconPath())));
        ImageView mainGView = new ImageView(mainGIcon);
        mainGView.setLayoutX(playerView.getScreenPlayerX().get());
        mainGView.setLayoutY(playerView.getScreenPlayerY().get() - 200);
        mainGView.setOnMouseClicked(event -> {
            System.out.println("Main Gauche cliqué");
            if (stuffClickListener != null) {
                stuffClickListener.onMainGClick();
            }
        });
        stuffPane.getChildren().add(mainGView);

        Image mainDIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getMainD() == null ? "/com/letop3/ktsh/images/item/mainDView.png" : stuff.getMainD().getIconPath())));
        ImageView mainDView = new ImageView(mainDIcon);
        mainDView.setLayoutX(playerView.getScreenPlayerX().get() + 40);
        mainDView.setLayoutY(playerView.getScreenPlayerY().get() - 200);
        mainDView.setOnMouseClicked(event -> {
            System.out.println("Main Droite cliqué");
            if (stuffClickListener != null) {
                stuffClickListener.onMainDClick();
            }
        });
        stuffPane.getChildren().add(mainDView);

        Image quickSlotIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getQuickSlot() == null ? "/com/letop3/ktsh/images/item/quickSlot.png" : stuff.getQuickSlot().getIconPath())));
        ImageView quickSlotView = new ImageView(quickSlotIcon);
        quickSlotView.setLayoutX(playerView.getScreenPlayerX().get() + 80);
        quickSlotView.setLayoutY(playerView.getScreenPlayerY().get() - 200);
        quickSlotView.setOnMouseClicked(event -> {
            System.out.println("QuickSlot cliqué");
            if (stuffClickListener != null) {
                stuffClickListener.onQuickSlotClick();
            }
        });
        stuffPane.getChildren().add(quickSlotView);

        for (int i = 0; i < stuff.getInventaire().size(); i++) {
            Image itemIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getInventaire().get(i).getIconPath())));
            ImageView imageView = new ImageView(itemIcon);
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);

            int row = i / 3;
            int col = i % 3;
            imageView.setLayoutX(playerView.getScreenPlayerX().get() + 200 + col * 32);
            imageView.setLayoutY(playerView.getScreenPlayerY().get() - 80 + row * 40);

            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                System.out.println("Stuff cliqué, nom : " + stuff.getInventaire().get(finalI).getNom());
                if (stuffClickListener != null) {
                    stuffClickListener.onStuffClick(finalI);
                }
            });

            stuffPane.getChildren().add(imageView);
        }
    }

    public void updateStuff(Stuff stuff) {
        drawStuff(stuff);
    }

    public void update() {
        playerView.update();

        for (Entity entity : groundView.getGround().getCurrentChunk().getEntities()) {
            entities.get(entity).update();
        }
        for (Chunk chunks : groundView.getGround().getCurrentChunk().getNeighbors()) {
            for (Entity entity : chunks.getEntities()) {
                entities.get(entity).update();
            }
        }
    }
}