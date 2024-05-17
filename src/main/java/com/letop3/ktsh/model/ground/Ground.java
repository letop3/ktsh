package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.files.MapLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;

public class Ground {
    private final Chunk[] chunks;
    private IntegerProperty currentChunkId;


    public Ground() {
        Chunk[] chunksTmp = null;

        try (MapLoader mapLoader = new MapLoader("src/main/resources/com/letop3/ktsh/ground.json")) {
            chunksTmp = mapLoader.getChunks();
        } catch (IOException ignored) {}

        this.chunks = chunksTmp;
        currentChunkId = new SimpleIntegerProperty();
    }

    public Chunk getChunk(int chunkNumber) {
        return chunks[chunkNumber];
    }

    public int getCurrentChunkId() {
        return currentChunkId.get();
    }

    public IntegerProperty currentChunkIdProperty() {
        return currentChunkId;
    }

    public void setCurrentChunkId(int currentChunkId) {
        this.currentChunkId.set(currentChunkId);
    }

    public boolean canMoveTo(int x, int y) {
        return true;
    }
}