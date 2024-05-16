package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.Updatable;

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
        return lastDirection;
    }

    public void update() {
        if (direction != null) {
            switch (direction) {
                case NORTH -> moveUp();
                case SOUTH -> moveDown();
                case EAST -> moveRight();
                case WEST -> moveLeft();
                case NORTH_WEST -> moveTopLeft();
                case NORTH_EAST -> moveTopRight();
                case SOUTH_WEST -> moveBottomLeft();
                case SOUTH_EAST -> moveBottomRight();
            }

            direction = null;
        }
    }

    public void move(ArrayList<Direction> displacements) {
        if (displacements.contains(Direction.NORTH) && displacements.contains(Direction.WEST)) {
            direction = Direction.NORTH_WEST;
        } else if (displacements.contains(Direction.NORTH) && displacements.contains(Direction.EAST)) {
            direction = Direction.NORTH_EAST;
        } else if (displacements.contains(Direction.SOUTH) && displacements.contains(Direction.WEST)) {
            direction = Direction.SOUTH_WEST;
        } else if (displacements.contains(Direction.SOUTH) && displacements.contains(Direction.EAST)) {
            direction = Direction.SOUTH_EAST;
        } else if (displacements.contains(Direction.WEST)) {
            direction = Direction.WEST;
        } else if (displacements.contains(Direction.EAST)) {
            direction = Direction.EAST;
        } else if (displacements.contains(Direction.NORTH)) {
            direction = Direction.NORTH;
        } else if (displacements.contains(Direction.SOUTH)) {
            direction = Direction.SOUTH;
        }
        lastDirection = direction;
    }

    private void moveTopLeft() {
        position.setX(position.getX() - 1);
        position.setY(position.getY() - 1);
    }

    private void moveTopRight() {
        position.setX(position.getX() + 1);
        position.setY(position.getY() - 1);
    }

    private void moveBottomLeft() {

        position.setX(position.getX() - 1);
        position.setY(position.getY() + 1);
    }

    private void moveBottomRight() {
        position.setX(position.getX() + 1);
        position.setY(position.getY() + 1);
    }

    private void moveLeft() {
        position.setX(position.getX() - 1);
    }

    private void moveRight() {
        position.setX(position.getX() + 1);
    }

    private void moveUp() {
        position.setY(position.getY() - 1);
    }

    private void moveDown() {
        position.setY(position.getY() + 1);
    }
}
