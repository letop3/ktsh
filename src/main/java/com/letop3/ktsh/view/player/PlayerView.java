package com.letop3.ktsh.view.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.ennemies.Mob;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.entity.player.PlayerListener;
import com.letop3.ktsh.view.GameView;
import com.letop3.ktsh.view.animation.AnimationHandler;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerView {
    private final ImageView playerImageView;
    private final AnimationHandler animHandler;
    private Pane gamePlayer;
    private GameView gameView;

    public PlayerView(Player player, Pane gamePlayer, GameView gameView) {
        animHandler = new AnimationHandler(new PlayerAnimationAdapter(player));
        this.gamePlayer = gamePlayer;
        this.gameView = gameView;

        this.playerImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(playerImageView);

        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());

        player.setPL(new PlayerListener() {
            @Override
            public void onAttack() {
                triggerAttackAnimation(player);
            }
        });

//        ImageView testNpcHB = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/hitbox.png"))));
//        testNpcHB.setLayoutX(player.getPosition().getX());
//        testNpcHB.setLayoutY(player.getPosition().getY());
//        testNpcHB.setFitHeight(playerImageView.getFitHeight());
//        testNpcHB.setFitWidth(playerImageView.getFitWidth());
//        gamePlayer.getChildren().add(testNpcHB);
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

    public void triggerAttackAnimation(Player player) {
        ImageView hitboxAtk = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/hitbox.png"))));
        Timer timer = new Timer();
        switch (player.getLastDirection()) {
            case NORTH, NORTH_EAST, NORTH_WEST:
                gamePlayer.getChildren().add(hitboxAtk);
                hitboxAtk.setFitHeight(24);
                hitboxAtk.setFitWidth(48);
                hitboxAtk.setX(getScreenPlayerX().get() - 8);
                hitboxAtk.setY(getScreenPlayerY().get() - 24);

                dmgToEntity(player, hitboxAtk);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitboxAtk));
                    }
                }, 150);
                break;
            case SOUTH, SOUTH_EAST, SOUTH_WEST:
                gamePlayer.getChildren().add(hitboxAtk);
                hitboxAtk.setFitHeight(24);
                hitboxAtk.setFitWidth(48);
                hitboxAtk.setX(getScreenPlayerX().get() - 8);
                hitboxAtk.setY(getScreenPlayerY().get() + 32);

                dmgToEntity(player, hitboxAtk);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitboxAtk));
                    }
                }, 150);
                break;
            case EAST:
                gamePlayer.getChildren().add(hitboxAtk);
                hitboxAtk.setFitHeight(48);
                hitboxAtk.setFitWidth(24);
                hitboxAtk.setX(getScreenPlayerX().get() + 30);
                hitboxAtk.setY(getScreenPlayerY().get() - 8);

                dmgToEntity(player, hitboxAtk);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitboxAtk));
                    }
                }, 150);
                break;
            case WEST:
                gamePlayer.getChildren().add(hitboxAtk);
                hitboxAtk.setFitHeight(48);
                hitboxAtk.setFitWidth(24);
                hitboxAtk.setX(getScreenPlayerX().get() - 20);
                hitboxAtk.setY(getScreenPlayerY().get() - 8);

                dmgToEntity(player, hitboxAtk);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> gamePlayer.getChildren().remove(hitboxAtk));
                    }
                }, 150);
                break;
        }
    }

    private void dmgToEntity(Player player, ImageView hitboxAtk){
        Iterator<Entity> iterator = player.getGround().getCurrentChunk().getEntities().iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            System.out.println(e);
            if (hitboxAtk.intersects(gameView.getEntities().get(e).getSpriteTarget().getBoundsInParent())){
                if (!(e instanceof Mob))
                    return;
                e.takeDamage(player.getAtk() + (player.getStuff().getMainG() == null ? 0 : player.getStuff().getMainG().getAtk()));
                System.out.println(e.getHp());
                if (e.getHp() <= 0) {
                    iterator.remove();
                    Pane entityView = gameView.getEntities().remove(e).getSpriteTarget();
                    Platform.runLater(() -> {
                        if (entityView != null && entityView.getParent() instanceof Pane) {
                            ((Pane) entityView.getParent()).getChildren().remove(entityView);
                        }
                    });
                }
            }
        }
    }

    public GameView getGameView() {
        return gameView;
    }
}
