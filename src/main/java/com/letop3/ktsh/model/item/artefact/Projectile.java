package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Direction;
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
        double norm = Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());

        double normX = direction.getX() / norm;
        double normY = direction.getY() / norm;

        position.setX(position.getX() + normX * 5);
        position.setY(position.getY() - normY * 5);
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }
}
