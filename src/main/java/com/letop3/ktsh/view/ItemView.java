package com.letop3.ktsh.view;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.item.artefact.Projectile;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ItemView {

    private Position screenPosition;
    private Image potionAtkEffectImg;
    private Image projoDinImg;
    private Image ermsEffectImg;
    private PlayerView playerView;
    private Pane itemEffectPane;
    private Pane gamePlayer;

    public ItemView(PlayerView playerView, Pane itemEffectPane, Pane gamePlayer, Position screenPosition) {
        this.itemEffectPane = itemEffectPane;
        this.playerView = playerView;
        this.gamePlayer = gamePlayer;
        this.screenPosition = screenPosition;
        this.ermsEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/erms.gif")));
        this.potionAtkEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/potionatk.gif")));
        this.projoDinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/dinprojo.gif")));
    }

    public Pane getItemEffectPane() {
        return itemEffectPane;
    }

    public void drawErmS() {
        double x = playerView.getScreenPlayerX().get();
        double y = playerView.getScreenPlayerY().get();
        ImageView ermsEffect = new ImageView(this.ermsEffectImg);
        ermsEffect.setLayoutX(x - 16);
        ermsEffect.setLayoutY(y - 16);
        gamePlayer.getChildren().add(ermsEffect);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> gamePlayer.getChildren().remove(ermsEffect));
            }
        }, 500);
    }

    public void drawPotionAtk() {
        double x = playerView.getScreenPlayerX().get();
        double y = playerView.getScreenPlayerY().get();
        ImageView potionAtkEffect = new ImageView(this.potionAtkEffectImg);
        potionAtkEffect.setLayoutX(x - 16);
        potionAtkEffect.setLayoutY(y - 16);
        gamePlayer.getChildren().add(potionAtkEffect);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> gamePlayer.getChildren().remove(potionAtkEffect));
            }
        }, 15000);
    }

    public void drawDinStaff(Env env) {
        ImageView projoDin = new ImageView(this.projoDinImg);
        projoDin.setLayoutX(env.getProjo().get(0).getPosition().getX() + screenPosition.getX());
        projoDin.setLayoutY(env.getProjo().get(0).getPosition().getY() + screenPosition.getY());
        itemEffectPane.getChildren().add(projoDin);

        dmgSpeToEntity(env.getPlayer(), projoDin, "FIRE");

        env.getProjo().get(0).getPosition().xProperty().addListener((obs, oldX, newX) -> {
            Platform.runLater(() ->
                    projoDin.setLayoutX(newX.doubleValue() + screenPosition.getX()));
        });
        env.getProjo().get(0).getPosition().yProperty().addListener((obs, oldY, newY) -> {
            Platform.runLater(() ->
                    projoDin.setLayoutY(newY.doubleValue() + screenPosition.getY()));
        });
        env.getProjo().addListener(new ListChangeListener<Projectile>() {
            @Override
            public void onChanged(Change<? extends Projectile> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        itemEffectPane.getChildren().remove(projoDin);
                    }
                }
            }
        });
    }

    private void dmgSpeToEntity(Player player, ImageView hitboxAtk, String type){
        Iterator<Entity> iterator = player.getGround().getCurrentChunk().getEntities().iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (hitboxAtk.intersects(playerView.getGameView().getEntities().get(e).getSpriteTarget().getBoundsInLocal())) {
                String faiblesse = e.getFaiblesse();
                String resistance = e.getResistance();

                if (type.equals(resistance))
                    e.takeDamage(0);
                else if (type.equals(faiblesse))
                    e.takeDamage(4);
                else
                    e.takeDamage(1);
            }
            System.out.println(e.getHp());
            if (e.getHp() <= 0) {
                iterator.remove();
                Pane entityView = playerView.getGameView().getEntities().remove(e).getSpriteTarget();
                Platform.runLater(() -> {
                    if (entityView != null && entityView.getParent() instanceof Pane) {
                        ((Pane) entityView.getParent()).getChildren().remove(entityView);
                    }
                });
            }
        }
    }
}
