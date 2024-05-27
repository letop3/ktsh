package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.utils.EntityBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chunk {
    public static final int CHUNK_SIZE = 352;
    private final int id;
    private final int[] tiles;
    //private final List<EntityBlock> entities;
    private final List<Chunk> neighbors;

    private int[] notaccesible = {1, 2};
    public Chunk(int id, int[] tiles) {
        this.id = id;
        this.tiles = tiles;
        //this.entities = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        //this.calculateEntities();
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

    /*
    private void calculateEntities() {
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int finalIndex = index;
                if (Arrays.stream(notaccesible).anyMatch(x -> x == tiles[finalIndex])) {
                    index ++;
                    continue;
                }
                System.out.println("i: " + i + " j: " + j + " index: " + index + " tile: " + tiles[index]);
                int x = j * 32;
                int y = i * 32;
                System.out.println("xchk: x: " + x + " y: " + y);
                entities.add(new EntityBlock(x, y));
                index ++;
            }
        }
    }


    public List<EntityBlock> getEntities() {
        return entities;
    }
    */

    @Override
    public String toString() {
        return "Chunk{" +
                "id=" + id +
                '}';
    }
}