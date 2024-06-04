package com.letop3.ktsh.model.ground;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
    public static final int CHUNK_SIZE = 352;

    private int id;
    private int[] tiles;
    private List<Chunk> neighbors;

    public Chunk(int id, int[] tiles) {
        this.id = id;
        this.tiles = tiles;
        this.neighbors = new ArrayList<>();
    }

    public void update() {

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