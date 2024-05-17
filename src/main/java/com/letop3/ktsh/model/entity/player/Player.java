package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;

public class Player extends Entity {
    public Player(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "Player";
    }

}
