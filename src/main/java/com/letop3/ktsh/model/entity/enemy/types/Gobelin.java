package com.letop3.ktsh.model.entity.enemy.types;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.enemy.Enemy;

public class Gobelin extends Enemy {
    public Gobelin(Position position) {
        super(position);
    }

    @Override
    public void attack() {

    }

    @Override
    public void defend() {

    }

    @Override
    public void move() {
        //TODO: A l'aide du BFS
    }

    @Override
    public String toString() {
        return "Gobelin";
    }
}
