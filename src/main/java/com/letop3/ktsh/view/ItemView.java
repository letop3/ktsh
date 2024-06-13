package com.letop3.ktsh.view;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ItemView {

    private Image potionAtkEffectImg;
    private Image projoDinImg;
    private Image ermsEffectImg;
    private PlayerView playerView;
    private Pane itemEffectPane;
    private Pane gamePlayer;

    public ItemView(PlayerView playerView, Pane itemEffectPane, Pane gamePlayer) {
        this.itemEffectPane = itemEffectPane;
        this.playerView = playerView;
        this.gamePlayer = gamePlayer;
        this.ermsEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/erms.gif")));
        this.potionAtkEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/potionatk.gif")));
        this.projoDinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/dinprojo.gif")));
    }

    public Pane getItemEffectPane() {
        return itemEffectPane;
    }

    public void drawErmS(){
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

    public void drawPotionAtk(){
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

    public void drawDinStaff(Env env){
        ImageView projoDin = new ImageView(this.projoDinImg);
        projoDin.setLayoutX(env.getProjo().get(0).getPosition().getX());
        projoDin.setLayoutY(env.getProjo().get(0).getPosition().getY());
        itemEffectPane.getChildren().add(projoDin);

        env.getProjo().get(0).getPosition().xProperty().addListener((obs, oldX, newX) -> {
            Platform.runLater(() -> projoDin.setLayoutX(newX.doubleValue()));
        });
        env.getProjo().get(0).getPosition().yProperty().addListener((obs, oldY, newY) -> {
            Platform.runLater(() -> projoDin.setLayoutY(newY.doubleValue()));
        });
    }
}
