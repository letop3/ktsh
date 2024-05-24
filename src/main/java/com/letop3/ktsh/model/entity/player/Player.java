package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;

public class Player extends Entity {
    private Stuff stuff;
    public Player(Position position) {
        super(position);
        this.stuff = new Stuff();
    }

    @Override
    public String toString() {
        return "Player";
    }

}
