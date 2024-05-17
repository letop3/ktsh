package com.letop3.ktsh.view;

import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.viewUtils.TilesetCutter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GroundView {
    private final TilesetCutter cutter;
    private final TilePane gameGround;
    private final Ground ground;

    public GroundView(Ground ground, TilePane gameGround) {
        this.ground = ground;
        this.gameGround = gameGround;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/tiles/ground.png", 32);
    }

    public void draw() {
        int[] tileArray = ground.getChunk(0, 0).getTiles();
        for (int j : tileArray) {
            Image tile = cutter.getTile(j);
            ImageView imageView = new ImageView(tile);
            gameGround.getChildren().add(imageView);
        }
    }

}
