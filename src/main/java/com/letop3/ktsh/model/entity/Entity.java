package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.Ground;
import com.letop3.ktsh.model.Updatable;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public abstract class Entity implements Updatable {
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

    public void update() {
        if (direction != null) {
            move(direction);
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

    private void move(Direction dir) {
        int newX = position.getX() + dir.getX();
        int newY = position.getY() - dir.getY();

        if (Ground.canMoveTo(newX, newY)) {
            position.setX(newX);
            position.setY(newY);
        }
    }
}
