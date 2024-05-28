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
        // taille tuile
        int tileSize = Chunk.CHUNK_SIZE / 11; // divisé par 11 pcq chunk de 11*11

        // coordoné cunk
        int chunkX = x / Chunk.CHUNK_SIZE;
        int chunkY = y / Chunk.CHUNK_SIZE;

        // pos relative des tuiles dans le chunk
        int relativeX = x % Chunk.CHUNK_SIZE;
        int relativeY = y % Chunk.CHUNK_SIZE;
        int tileX = relativeX / tileSize;
        int tileY = relativeY / tileSize;

        // jai pas compris mais ca marche
        // ca régle un pb de décalage entre vue terrain / collisions
        if (relativeX == 0 && tileX > 0) tileX--;
        if (relativeY == 0 && tileY > 0) tileY--;

        // en dehors de map
        if (chunkX < 0 || chunkX >= MAP_WIDTH || chunkY < 0 || chunkY >= MAP_HEIGHT) {
            return false;
        }

        Chunk chunk = getChunk(chunkX, chunkY);
        if (chunk == null) {
            return false;
        }

        if (tileX < 0 || tileX >= 11 || tileY < 0 || tileY >= 11) { // 11x11 tiles in each chunk
            return false;
        }

        int tileIndex = tileY * 11 + tileX;

        if (tileIndex < 0 || tileIndex >= chunk.getTiles().length) {
            return false;
        }

        int tileValue = chunk.getTiles()[tileIndex];

        boolean canMove = tileValue != 1 && tileValue != 2;
        return canMove;
    }




}