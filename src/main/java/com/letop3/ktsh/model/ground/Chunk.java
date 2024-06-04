package com.letop3.ktsh.model.ground;

import java.util.ArrayList;
import java.util.List;

import com.letop3.ktsh.model.entity.Entity;

public class Chunk {
    public static final int CHUNK_SIZE = 352;

    private int id;
    private int[] tiles;
    private List<Chunk> neighbors;

    private List<Entity> entities;

    public Chunk(int id, int[] tiles) {
        this.id = id;
        this.tiles = tiles;
        this.neighbors = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
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

    @Override
    public String toString() {
        return "Chunk{" +
                "id=" + id +
                '}';
    }
}