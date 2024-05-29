package com.letop3.ktsh.model;


import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.entity.enemy.types.Gobelin;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;

import java.util.ArrayList;
import java.util.Collection;

public class Env {
    private final Ground ground;
    private final Player player;

    private final ArrayList<Enemy> enemies;

    public Env() {
        this.player = new Player(new Position((int)(Chunk.CHUNK_SIZE * 1.45), (int)(Chunk.CHUNK_SIZE * 1.45)));
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Gobelin(new Position((int) (Chunk.CHUNK_SIZE * 1.45), (int) (Chunk.CHUNK_SIZE * 1.45))));
        this.ground = new Ground(player);
    }

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
