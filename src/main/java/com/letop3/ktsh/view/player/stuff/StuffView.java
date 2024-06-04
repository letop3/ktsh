package com.letop3.ktsh.view.player.stuff;

import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.view.StuffClickListener;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StuffView {
    private Pane stuffPane;
    private PlayerView playerView;
    private StuffClickListener stuffClickListener;
    private BooleanProperty isVisible = new SimpleBooleanProperty(false);

    public StuffView(Pane stuffPane, PlayerView playerView) {
        this.stuffPane = stuffPane;
        this.playerView = playerView;
    }

    public void setStuffClickListener(StuffClickListener stuffClickListener) {
        this.stuffClickListener = stuffClickListener;
    }

    private void drawStuff(Stuff stuff) {
        stuffPane.getChildren().clear();

        Image mainGIcon;
        if (stuff.getMainG() == null){
            mainGIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/mainGView.png")));
        } else {
            mainGIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getMainG().getIconPath())));
        }
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

        Image mainDIcon;
        if (stuff.getMainD() == null){
            mainDIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/mainDView.png")));
        } else {
            mainDIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getMainD().getIconPath())));
        }
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

        Image quickSlotIcon;
        if (stuff.getQuickSlot() == null){
            quickSlotIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/item/quickSlot.png")));
        } else {
            quickSlotIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(stuff.getQuickSlot().getIconPath())));
        }
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

            if (i < 3) {
                imageView.setLayoutX(playerView.getScreenPlayerX().get() + 200 + i * 32);
                imageView.setLayoutY(playerView.getScreenPlayerY().get() - 80);
            } else if (i < 6) {
                imageView.setLayoutX(playerView.getScreenPlayerX().get() + 200 + (i - 3) * 32);
                imageView.setLayoutY(playerView.getScreenPlayerY().get() - 40);
            } else if (i < 9) {
                imageView.setLayoutX(playerView.getScreenPlayerX().get() + 200 + (i - 6) * 32);
                imageView.setLayoutY(playerView.getScreenPlayerY().get());
            } else {
                imageView.setLayoutX(playerView.getScreenPlayerX().get() + 200 + (i - 9) * 32);
                imageView.setLayoutY(playerView.getScreenPlayerY().get() + 40);
            }

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
    public void toogleVisibility(){
        stuffPane.setVisible(!isVisible.get());
        isVisible.setValue(!isVisible.get());
    }

    public boolean isIsVisible() {
        return isVisible.get();
    }

    public BooleanProperty isVisibleProperty() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible.set(isVisible);
    }
}
