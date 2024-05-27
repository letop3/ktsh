package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.files.MapLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;

public class Ground {
    public static final int MAP_WIDTH = 3, MAP_HEIGHT = 3;

    private final Chunk[][] chunks;
    private final IntegerProperty currentChunkIdX;
    private final IntegerProperty currentChunkIdY;


    public Ground(Player player) {
        this.chunks = new Chunk[MAP_HEIGHT][MAP_WIDTH];
        try (MapLoader mapLoader = new MapLoader("src/main/resources/com/letop3/ktsh/ground.json")) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                for (int x = 0; x < MAP_WIDTH; x++) {
                    this.chunks[y][x] = mapLoader.getChunkById(1 + x + MAP_HEIGHT * y);
                }
            }
        } catch (IOException ignored) {}

        currentChunkIdX = new SimpleIntegerProperty(player.getPosition().getX() / Chunk.CHUNK_SIZE);
        currentChunkIdY = new SimpleIntegerProperty(player.getPosition().getY() / Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            currentChunkIdX.set((int)nouv / Chunk.CHUNK_SIZE);
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            currentChunkIdY.set((int)nouv / Chunk.CHUNK_SIZE);
        });
    }

    public Chunk getChunk(int x, int y) {
        return chunks[y][x];
    }

    public int getCurrentChunkIdX() {
        return currentChunkIdX.get();
    }

    public int getCurrentChunkIdY() {
        return currentChunkIdY.get();
    }

    public IntegerProperty currentChunkIdXProperty() {
        return currentChunkIdX;
    }

    public IntegerProperty currentChunkIdYProperty() {
        return currentChunkIdY;
    }

    public void setCurrentChunkIdY(int currentChunkIdX) {
        this.currentChunkIdX.set(currentChunkIdX);
    }

    public void setCurrentChunkIdX(int currentChunkIdY) {
        this.currentChunkIdY.set(currentChunkIdY);
    }

    public boolean canMoveTo(int x, int y) {
        return true;
    }
}