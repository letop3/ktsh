package com.letop3.ktsh.view;

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
    private Image ermsEffectImg;
    private PlayerView playerView;
    private Pane itemEffectPane;

    public ItemView(PlayerView playerView, Pane itemEffectPane) {
        this.itemEffectPane = itemEffectPane;
        this.playerView = playerView;
        this.ermsEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/erms.gif")));
        this.potionAtkEffectImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/potionatk.gif")));
    }

    public void drawErmS(){
        double x = playerView.getScreenPlayerX().get();
        double y = playerView.getScreenPlayerY().get();
        ImageView ermsEffect = new ImageView(this.ermsEffectImg);
        ermsEffect.setLayoutX(x - 16);
        ermsEffect.setLayoutY(y - 16);
        itemEffectPane.getChildren().add(ermsEffect);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> itemEffectPane.getChildren().remove(ermsEffect));
            }
        }, 500);
    }

    public void drawPotionAtk(){
        double x = playerView.getScreenPlayerX().get();
        double y = playerView.getScreenPlayerY().get();
        ImageView potionAtkEffect = new ImageView(this.potionAtkEffectImg);
        potionAtkEffect.setLayoutX(x - 16);
        potionAtkEffect.setLayoutY(y - 16);
        itemEffectPane.getChildren().add(potionAtkEffect);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> itemEffectPane.getChildren().remove(potionAtkEffect));
            }
        }, 15000);
    }
}
