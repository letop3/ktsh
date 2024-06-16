package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;

public abstract class Interractible extends Entity {
    private final static double RANGE = 50;

    public Interractible(Position position, Ground ground) {
        super(position, ground);
    }

    public boolean isInterractible(Player player) {
        return this.getPosition().distance(player.getPosition()) <= RANGE;
    }

    public abstract void interract();
}
