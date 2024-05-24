package com.letop3.ktsh.view;

import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.viewUtils.TilesetCutter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GroundView {
    private final TilesetCutter cutter;
    private final TilePane gameGround;
    private final TilePane[][] chunks;
    private final Ground ground;

    public GroundView(Ground ground, TilePane gameGround) {
        this.ground = ground;
        this.gameGround = gameGround;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/tiles/ground.png", 32);
        this.chunks = new TilePane[3][3];

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                chunks[x][y] = new TilePane();
                chunks[x][y].setPrefTileWidth(32);
                chunks[x][y].setPrefTileHeight(32);
                chunks[x][y].setPrefWidth(352);
                chunks[x][y].setPrefHeight(352);

                loadChunkInto(chunks[x][y], x + 3 * y);
                gameGround.getChildren().add(chunks[x][y]);
            }
        }

        ground.currentChunkIdProperty().addListener((obs, old, nouv) -> {
            gameGround.getChildren().clear();
            loadChunkInto(chunks[1][1], (int)nouv);
        });
    }

    private void loadChunkInto(TilePane dest, int chunkNumber) {
        if (chunkNumber < ground.getChunkNumber()) {
            System.out.println("loading " + chunkNumber);
            for (int j : ground.getChunk(chunkNumber).getTiles()) {
                Image tile = cutter.getTile(j);
                ImageView imageView = new ImageView(tile);
                dest.getChildren().add(imageView);
            }
        }
    }

    public void update() {

    }
}
