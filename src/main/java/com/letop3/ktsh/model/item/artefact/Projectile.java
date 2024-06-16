package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.Updatable;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

public class Projectile extends Updatable {
    private Direction direction;
    private Position position;

    public Projectile(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    protected void update(long frame) {
        double norm = Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());

        double normX = direction.getX() / norm;
        double normY = direction.getY() / norm;

        position.setX(position.getX() + normX * 5);
        position.setY(position.getY() - normY * 5);
    }
}
