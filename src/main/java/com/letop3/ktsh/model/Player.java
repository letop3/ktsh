package com.letop3.ktsh.model;

public class Player {
    private final Position position;

    public Player(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public char getDirection() {
        return position.getDirection();
    }

    public void moveTopLeft() {
        if (Ground.canMoveTo(position.getX() - 1, position.getY() - 1)) {
            position.setX(position.getX() - 1);
            position.setY(position.getY() - 1);
            position.setDirection('s');
        }
    }

    public void moveTopRight() {
        if (Ground.canMoveTo(position.getX() + 1, position.getY() - 1)) {
            position.setX(position.getX() + 1);
            position.setY(position.getY() - 1);
            position.setDirection('s');
        }
    }

    public void moveBottomLeft() {
        if (Ground.canMoveTo(position.getX() - 1, position.getY() + 1)) {
            position.setX(position.getX() - 1);
            position.setY(position.getY() + 1);
            position.setDirection('n');
        }
    }

    public void moveBottomRight() {
        if (Ground.canMoveTo(position.getX() + 1, position.getY() + 1)) {
            position.setX(position.getX() + 1);
            position.setY(position.getY() + 1);
            position.setDirection('n');
        }
    }

    public void moveLeft() {
        if (Ground.canMoveTo(position.getX() - 1, position.getY())) {
            position.setX(position.getX() - 1);
            position.setDirection('e');
        }
    }

    public void moveRight() {
        if (Ground.canMoveTo(position.getX() + 1, position.getY())) {
            position.setX(position.getX() + 1);
            position.setDirection('w');
        }
    }

    public void moveUp() {
        if (Ground.canMoveTo(position.getX(), position.getY() - 1)) {
            position.setY(position.getY() - 1);
            position.setDirection('s');
        }
    }

    public void moveDown() {
        if (Ground.canMoveTo(position.getX(), position.getY() + 1)) {
            position.setY(position.getY() + 1);
            position.setDirection('n');
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                '}';
    }

}
