package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.files.JsonLoader;
import com.letop3.ktsh.model.files.MapLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Ground {
    private MapLoader mapLoader;
    public final Chunk[] chunks;

    public Ground() {
        Chunk[] chunks1 = null;
        try (MapLoader mapLoader = new MapLoader("src/main/resources/com/letop3/ktsh/ground.json")) {
            chunks1 = mapLoader.getChunks();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
        this.chunks = chunks1;
    }

    public Chunk getChunk(int x, int y) {
        return chunks[x];
    }

    public boolean canMoveTo(int x, int y) {
        return true;
    }
}