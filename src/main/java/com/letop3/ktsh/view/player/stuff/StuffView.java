package com.letop3.ktsh.view.player.stuff;

import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.view.StuffClickListener;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StuffView {
    private Pane slotPane;
    private Pane stuffPane;
    private PlayerView playerView;
    private StuffClickListener stuffClickListener;
    private BooleanProperty isVisible = new SimpleBooleanProperty(false);

    public StuffView(Pane stuffPane, Pane slotPane, PlayerView playerView) {
        this.stuffPane = stuffPane;
        this.playerView = playerView;
        this.slotPane = slotPane;
    }

    public void setStuffClickListener(StuffClickListener stuffClickListener) {
        this.stuffClickListener = stuffClickListener;
    }

    private void drawStuff(Stuff stuff) {
        addStuffImage(stuff.getMainG(), "/com/letop3/ktsh/images/item/mainGView.png", 0, -200, () -> {
            System.out.println("Main Gauche cliqué");
            if (stuffClickListener != null) stuffClickListener.onMainGClick();
        });

        addStuffImage(stuff.getMainD(), "/com/letop3/ktsh/images/item/mainDView.png", 40, -200, () -> {
            System.out.println("Main Droite cliqué");
            if (stuffClickListener != null) stuffClickListener.onMainDClick();
        });

        addStuffImage(stuff.getQuickSlot(), "/com/letop3/ktsh/images/item/quickSlot.png", 80, -200, () -> {
            System.out.println("QuickSlot cliqué");
            if (stuffClickListener != null) stuffClickListener.onQuickSlotClick();
        });

        for (int i = 0; i < Stuff.TAILLE_INVENTAIRE; i++) {
            ImageView imageView;
            if (i < stuff.getInventaire().size()) {
                imageView = createImageView(stuff.getInventaire().get(i).getIconPath(), 32, 32);
                int finalI = i;
                imageView.setOnMouseClicked(event -> {
                    System.out.println("Stuff cliqué, nom : " + stuff.getInventaire().get(finalI).getNom());
                    if (stuffClickListener != null) stuffClickListener.onStuffClick(finalI);
                });
            } else {
                imageView = createImageView("/com/letop3/ktsh/images/item/empty.png", 32, 32);
            }
            setPosition(imageView, i);
            stuffPane.getChildren().add(imageView);
        }
    }

    private void drawSlot(Stuff stuff) {
        addSlotImage(stuff.getMainG(), "/com/letop3/ktsh/images/item/mainGView.png", 260, -200);
        addSlotImage(stuff.getMainD(), "/com/letop3/ktsh/images/item/mainDView.png", 260, -150);
        addSlotImage(stuff.getQuickSlot(), "/com/letop3/ktsh/images/item/quickSlot.png", 210, -175);
    }

    private void addStuffImage(Item item, String defaultPath, double offsetX, double offsetY, Runnable onClick) {
        String path = item == null ? defaultPath : item.getIconPath();
        ImageView imageView = createImageView(path, -1, -1);
        imageView.setLayoutX(playerView.getScreenPlayerX().get() + offsetX);
        imageView.setLayoutY(playerView.getScreenPlayerY().get() + offsetY);
        imageView.setOnMouseClicked(event -> onClick.run());
        stuffPane.getChildren().add(imageView);
    }

    private void addSlotImage(Item item, String defaultPath, double offsetX, double offsetY) {
        String path = item == null ? defaultPath : item.getIconPath();
        ImageView imageView = createImageView(path, 48, 48);
        imageView.setLayoutX(playerView.getScreenPlayerX().get() + offsetX);
        imageView.setLayoutY(playerView.getScreenPlayerY().get() + offsetY);
        slotPane.getChildren().add(imageView);
    }

    private ImageView createImageView(String path, double width, double height) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        ImageView imageView = new ImageView(image);
        if (width > 0 && height > 0) {
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
        }
        return imageView;
    }

    private void setPosition(ImageView imageView, int index) {
        double baseX = playerView.getScreenPlayerX().get() + 200;
        double baseY = playerView.getScreenPlayerY().get();
        double x = baseX + (index % 3) * 37;
        double y = baseY - 80 + (index / 3) * 40;
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    public void updateStuff(Stuff stuff) {
        stuffPane.getChildren().clear();
        drawStuff(stuff);
        drawSlot(stuff);
    }

    public void toogleVisibility(){
        stuffPane.setVisible(!isVisible.get());
        slotPane.setVisible(isVisible.get());
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
