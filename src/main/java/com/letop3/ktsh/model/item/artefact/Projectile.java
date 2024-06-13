package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.ground.Ground;

public class Projectile {
    private Direction direction;
    private Position position;
    private Ground ground;

    public Projectile(Position position, Ground ground, Direction direction) {
        this.position = position;
        this.direction = direction;
        this.ground = ground;
    }

    public void update() {
        position.setX(position.getX() + direction.getX()*3);
        position.setY(position.getY() - direction.getY()*3);
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }
}
