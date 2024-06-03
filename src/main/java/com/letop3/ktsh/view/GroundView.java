package com.letop3.ktsh.view;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.viewUtils.TilesetCutter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.beans.value.ChangeListener;

public class GroundView {
    private final TilesetCutter cutter;
    private final TilePane[][] chunks;
    private final Ground ground;

    public GroundView(Ground ground, TilePane gameGround, Player player) {
        this.ground = ground;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/tiles/ground.png", 32);
        this.chunks = new TilePane[3][3];

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                chunks[x][y] = new TilePane();
                chunks[x][y].setPrefTileWidth(32);
                chunks[x][y].setPrefTileHeight(32);
                chunks[x][y].setPrefWidth(Chunk.CHUNK_SIZE);
                chunks[x][y].setPrefHeight(Chunk.CHUNK_SIZE);

                loadChunkInto(chunks[x][y], ground.getCurrentChunkIdX() - x + 1, ground.getCurrentChunkIdY() - y + 1);
                gameGround.getChildren().add(chunks[x][y]);
            }
        }

        ChangeListener<Number> chunkUpdate = (obs, old, nouv) -> {
            int chunkX = ground.getCurrentChunkIdX(), chunkY = ground.getCurrentChunkIdY();
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    chunks[x][y].getChildren().clear();
                    loadChunkInto(chunks[x][y], chunkX + x - 1, chunkY + y - 1);
                }
            }
        };

        ground.currentChunkIdXProperty().addListener(chunkUpdate);
        ground.currentChunkIdYProperty().addListener(chunkUpdate);
    }

    private void loadChunkInto(TilePane dest, int x, int y) {
        if (x >= 0 && x < Ground.MAP_WIDTH && y >= 0 && y < Ground.MAP_HEIGHT) {
            for (int j : ground.getChunk(x, y).getTiles()) {
                Image tile = cutter.getTile(j);
                ImageView imageView = new ImageView(tile);
                dest.getChildren().add(imageView);
            }
        }
    }
}
