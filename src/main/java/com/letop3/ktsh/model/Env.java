package com.letop3.ktsh.model;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;

public class Env {
    private final Ground ground;
    private final Player player;

    public Env() {
        this.ground = new Ground();
        this.player = new Player(new Position((int)(Chunk.CHUNK_SIZE * 1.45), (int)(Chunk.CHUNK_SIZE * 1.45)), ground);
        ground.setPlayer(player);

        addEntity(new NPC(new Position(252, 512), ground, null));
    }

    public void update() {
        player.update();
		ground.getCurrentChunk().update();
        for (Chunk chunks : ground.getCurrentChunk().getNeighbors()) {
            chunks.update();
        }
    }

	public void addEntity(Entity entity) {
		entity.getPosition().xProperty().addListener((obs, old, nouv) -> {
			if ((int)((int)old / Chunk.CHUNK_SIZE) != (int)((int)nouv / Chunk.CHUNK_SIZE)) {
				ground.getChunkFromPos((double)old, entity.getPosition().getY()).removeEntity(entity);
				ground.getChunkFromPos((double)nouv, entity.getPosition().getY()).addEntity(entity);
			}
		});
		entity.getPosition().yProperty().addListener((obs, old, nouv) -> {
			if ((int)((int)old / Chunk.CHUNK_SIZE) != (int)((int)nouv / Chunk.CHUNK_SIZE)) {
				ground.getChunkFromPos(entity.getPosition().getX(), (double)old).removeEntity(entity);
				ground.getChunkFromPos(entity.getPosition().getX(), (double)nouv).addEntity(entity);
			}
		});

		ground.getChunkFromPos(entity.getPosition().getX(), entity.getPosition().getY()).addEntity(entity);
	}

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }
}
