package com.letop3.ktsh.model.ground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.letop3.ktsh.model.entity.Entity;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class Chunk {
    public static final int CHUNK_SIZE = 352;

    private int id;
    private int[] tiles;
    private List<Chunk> neighbors;

    private ObservableList<Entity> entities;

    public Chunk(int id, int[] tiles) {
        this.id = id;
        this.tiles = tiles;
        this.neighbors = new ArrayList<>();
        this.entities = FXCollections.observableArrayList();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public ObservableList<Entity> getEntities() {
        return entities;
    }

    public void update() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();
            Chunk currentChunk = entity.getGround().getChunkFromPos(entity.getPosition().getX(), entity.getPosition().getY());
            if (currentChunk != this) {
                iterator.remove();
                currentChunk.addEntity(entity);
            }
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