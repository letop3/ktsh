package com.letop3.ktsh.view;

import com.letop3.ktsh.model.Ground;
import com.letop3.ktsh.utils.TilesetCutter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GroundView {
    private final TilesetCutter cutter;
    private final TilePane gameGround;

    public GroundView(TilePane gameGround) {
        this.gameGround = gameGround;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/tiles/ground.png", 32);
    }

    public void draw() {
        int[] tileArray = Ground.GROUND;
        for (int j : tileArray) {
            Image tile = cutter.getTile(j);
            ImageView imageView = new ImageView(tile);
            gameGround.getChildren().add(imageView);
        }
    }

}
