package com.letop3.ktsh.model.entity.ennemies.mobs;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.entity.player.Player;

public class Knight extends Enemy {
    public Knight(Position position, Player player) {
        super(position, player, 5, 3);
    }

    protected Knight(Position position, Player player, int health, int damage) {
        super(position, player, health, damage);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
