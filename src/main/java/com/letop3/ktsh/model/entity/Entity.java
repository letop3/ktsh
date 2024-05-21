package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.Collidable;
import com.letop3.ktsh.model.Updatable;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.utils.EntityBlock;


public abstract class Entity implements Updatable, Collidable {
    private final Position position;
    private Direction direction;
    private final EntityBlock bounds;

    public Entity(Position position) {
        this.position = position;
        this.bounds = new EntityBlock(position.getX(), position.getY());
        this.direction = null;
    }

    public EntityBlock getBounds() {
        return bounds;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void update(Ground ground) {
        if (direction != null) {
            int newX = position.getX() + direction.getX();
            int newY = position.getY() - direction.getY();

            if (ground.canMoveTo(newX, newY)) {
                position.setX(newX);
                position.setY(newY);
            }
        }
    }

    public void addDirection(Direction direction) {
        if (this.direction == null) this.direction = direction;
        else this.direction = this.direction.add(direction);
    }

    public void remDirection(Direction direction) {
        if (this.direction == null) this.direction = direction;
        else this.direction = this.direction.sub(direction);
    }
}
