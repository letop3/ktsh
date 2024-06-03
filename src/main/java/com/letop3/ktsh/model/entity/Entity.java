package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.Updatable;

public abstract class Entity implements Updatable {
    private final static double speed = 2;

    private final Position position;
    private Direction direction;

    public Entity(Position position) {
        this.position = position;
        this.direction = null;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void update(Ground ground) {
        if (direction != null) {
            double newX = position.getX();
            double newY = position.getY();

            if (direction.isDiagonal()) {
                double diagonalMove = Math.sqrt(0.5);
                newX += direction.getX() * diagonalMove * speed;
                newY -= direction.getY() * diagonalMove * speed;
            }
            else {
                newX += direction.getX() * speed;
                newY -= direction.getY() * speed;
            }

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
        if (this.direction != null && this.direction.getX() - direction.getX() == 0 && this.direction.getY() - direction.getY() == 0) {
            this.direction = this.direction.sub(direction);
        }
    }
}
