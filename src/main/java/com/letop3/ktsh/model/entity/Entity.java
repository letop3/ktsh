package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.Ground;
import com.letop3.ktsh.model.Updatable;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public abstract class Entity implements Updatable {
    private final Position position;
    private Direction direction;

    private Direction lastDirection;

    public Entity(Position position) {
        this.position = position;
        this.direction = null;
        this.lastDirection = null;
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
