package com.letop3.ktsh.model;

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

        ground.getCurrentChunk().addEntity(new NPC(new Position(452, 512), ground, null));
    }

    public void update() {
        player.update();
        for (Chunk chunks : ground.getCurrentChunk().getNeighbors()) {
            chunks.update();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }
}
