package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.utils.EntityBlock;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
    private final int id;
    private final int[] tiles;
    private final int width;
    private final int height;
    private final List<EntityBlock> entities;
    private final List<Chunk> neighbors;

    public Chunk(int id, int[] tiles, int width, int height) {
        this.id = id;
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        this.calculateEntities();
    }

    public int getId() {
        return id;
    }

    public int[] getTiles() {
        return tiles;
    }

    public List<Chunk> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Chunk neighbor) {
        this.neighbors.add(neighbor);
    }

    private void calculateEntities() {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != 0) {
                int x = i % width;
                int y = i / width;
                entities.add(new EntityBlock(x, y));
            }
        }
    }

    public List<EntityBlock> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "id=" + id +
                '}';
    }
}