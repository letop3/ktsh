package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.entity.BlockM;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.files.MapLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ground {
    public static final int MAP_WIDTH = 3, MAP_HEIGHT = 3;

    private final Chunk[][] chunks;
    private final IntegerProperty currentChunkIdX;
    private final IntegerProperty currentChunkIdY;

    private Player player;

    public Ground() {
        this.chunks = new Chunk[MAP_HEIGHT][MAP_WIDTH];
        try (MapLoader mapLoader = new MapLoader("src/main/resources/com/letop3/ktsh/ground.json")) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                for (int x = 0; x < MAP_WIDTH; x++) {
                    this.chunks[y][x] = mapLoader.getChunkById(1 + x + MAP_HEIGHT * y);
                }
            }
        } catch (IOException ignored) {}

        currentChunkIdX = new SimpleIntegerProperty();
        currentChunkIdY = new SimpleIntegerProperty();

        this.player = null;
    }

    public void setPlayer(Player player) {
        currentChunkIdX.set((int)player.getPosition().getX() / Chunk.CHUNK_SIZE);
        currentChunkIdY.set((int)player.getPosition().getY() / Chunk.CHUNK_SIZE);

        player.getPosition().xProperty().addListener((obs, old, nouv) -> {
            currentChunkIdX.set((int)((double)nouv / Chunk.CHUNK_SIZE));
        });
        player.getPosition().yProperty().addListener((obs, old, nouv) -> {
            currentChunkIdY.set((int)((double)nouv / Chunk.CHUNK_SIZE));
        });

        this.player = player;
    }

    public Chunk getChunk(int x, int y) {
        return chunks[y][x];
    }

    public Chunk[][] getChunks() {
        return chunks;
    }

    public Chunk getCurrentChunk() {
        return chunks[currentChunkIdY.get()][currentChunkIdX.get()];
    }

    public List<Chunk> getCurrentChunks() {
        List<Chunk> currentChunks = new ArrayList<>();
        currentChunks.add(getCurrentChunk());
        currentChunks.addAll(getCurrentChunk().getNeighbors());
        return currentChunks;
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

    public Chunk getChunkFromPos(double x, double y) {
        int chunkX = (int)(x / Chunk.CHUNK_SIZE);
        int chunkY = (int)(y / Chunk.CHUNK_SIZE);
        return getChunk(chunkX, chunkY);
    }

    public int tileFromPosX(double x) {
        return (int)(x / (Chunk.CHUNK_SIZE / 11));
    }

    public int tileFromPosY(double y) {
        return (int)(y / (Chunk.CHUNK_SIZE / 11));
    }

    public double posXFromTile(int x) {
        return x * (Chunk.CHUNK_SIZE / 11) + 16;
    }

    public double posYFromTile(int y) {
        return y * (Chunk.CHUNK_SIZE / 11) + 16;
    }

    public boolean isTileWalkable(double x, double y, Direction direction){
        if (direction == null)
            return true;

        if (direction.isDiagonal()) {
            return isTileWalkable1P(x + direction.getX() * 16, y - direction.getY() * 16) &&
                    isTileWalkable1P(x + direction.getX() * 16, y) &&
                    isTileWalkable1P(x, y - direction.getY() * 16);
        }
        
        return isTileWalkable1P(x + direction.getX() * 16, y - direction.getY() * 16);
    }

    public boolean isTileWalkable1P(double x, double y) {
        int tileSize = Chunk.CHUNK_SIZE / 11; // divisé par 11 pcq chunk de 11*11

        // en dehors de map
        if (x < 0 || x > MAP_WIDTH * Chunk.CHUNK_SIZE || y < 0 || y > MAP_HEIGHT * Chunk.CHUNK_SIZE) {
            return false;
        }

        // coordoné chunk
        int chunkX = (int) (x / Chunk.CHUNK_SIZE);
        int chunkY = (int) (y / Chunk.CHUNK_SIZE);

        // pos relative des tuiles dans le chunk
        double relativeX = x % Chunk.CHUNK_SIZE;
        double relativeY = y % Chunk.CHUNK_SIZE;
        int tileX = (int) (relativeX / tileSize);
        int tileY = (int) (relativeY / tileSize);

        // Adjust for tile index
        if (relativeX == 0 && tileX > 0) tileX--;
        if (relativeY == 0 && tileY > 0) tileY--;

        if (tileX < 0 || tileY < 0) {
            return false;
        }

        Chunk chunk = getChunk(chunkX, chunkY);
        if (chunk == null) {
            return false;
        }

        int tileIndex = tileY * 11 + tileX;

        if (tileIndex >= chunk.getTiles().length) {
            return false;
        }

        int tileValue = chunk.getTiles()[tileIndex];

        return tileValue != 1 && tileValue != 2;
    }

    public double[] getFinalPositionAfterCollision(double startX, double startY, Direction direction, double speed, Entity e) {
        double diagonalMove = direction.isDiagonal() ? Math.sqrt(0.5) : 1;
        double stepX = direction.getX() * diagonalMove * speed;
        double stepY = direction.getY() * diagonalMove * speed;

        boolean passX = direction.getX() != 0 && isTileWalkable(startX + stepX, startY, e.getDirection());
        boolean passY = direction.getY() != 0 && isTileWalkable(startX, startY - stepY, e.getDirection());

		if (e instanceof Enemy) {
            Bounds hitbox = new BoundingBox(player.getPosition().getX(), player.getPosition().getY(), player.getHitbox().getWidth(), player.getHitbox().getHeight());
            if (e.getHitbox().intersects(hitbox)) {
                if (passX) passX = Math.abs(player.getPosition().getX() - startX) < Math.abs(player.getPosition().getX() - (startX + stepX));
                if (passY) passY = Math.abs(player.getPosition().getY() - startY) < Math.abs(player.getPosition().getY() - (startY - stepY));
            }
        }
		else if (e instanceof Player) {
			Bounds hitbox = new BoundingBox(startX + stepX, startY + stepY, player.getHitbox().getWidth(), player.getHitbox().getHeight());
			for (Chunk chunk : this.getCurrentChunks()) {
				for (Entity entity : chunk.getEntities()) {
					if (entity instanceof BlockM && entity.getHitbox().intersects(hitbox)) {
						if (passX) passX = Math.abs(entity.getPosition().getX() - startX) < Math.abs(entity.getPosition().getX() - (startX + stepX));
						if (passY) passY = Math.abs(entity.getPosition().getY() - startY) < Math.abs(entity.getPosition().getY() - (startY - stepY));
					}
				}
			}
		}

        return new double[] {passX ? startX + stepX : startX, passY ? startY - stepY : startY};
    }
}