package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.Updatable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class Entity implements Updatable {
    private final static double speed = 2;
    private final Ground ground;
    private final Position position;
    private Direction direction;
    private Direction lastDirection;

    private double hitboxWidth, hitboxHeight;
    private Bounds hitbox;

    public Entity(Position position, Ground ground) {
        this.position = position;
        this.ground = ground;

        this.direction = null;
        this.lastDirection = Direction.SOUTH;

        this.hitboxWidth = 64;
        this.hitboxHeight = 64;
        this.hitbox = new BoundingBox(position.getX() + (Chunk.CHUNK_SIZE / 11), position.getY() + (Chunk.CHUNK_SIZE / 11), hitboxWidth, hitboxHeight);
    }

    public Position getPosition() {
        return position;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Ground getGround() {
        return ground;
    }

    public double[] predictPosition(Direction direction) {
        if (direction != null) {
            return ground.getFinalPositionAfterCollision(position.getX(), position.getY(), direction, speed, this);
        }
        return new double[] {position.getX(), position.getY()};
    }

    public void update() {
        double[] newPos = predictPosition(direction);
        position.setX(newPos[0]);
        position.setY(newPos[1]);
        if (direction != null) {
            lastDirection = direction;
            hitbox = new BoundingBox(position.getX() + (Chunk.CHUNK_SIZE / 11), position.getY() + (Chunk.CHUNK_SIZE / 11), hitboxWidth, hitboxHeight);
        }
    }

    public void addDirection(Direction direction) {
        if (this.direction == null) this.direction = direction;
        else this.direction = this.direction.add(direction);
    }

    public void remDirection(Direction direction) {
        if (direction != null && this.direction != null && (this.direction.getX() - direction.getX() == 0 || this.direction.getY() - direction.getY() == 0)) {
            this.direction = this.direction.sub(direction);
        }
    }

    public void setHitboxSize(double width, double height) {
        hitboxWidth = width;
        hitboxHeight = height;
        hitbox = new BoundingBox(position.getX() + (Chunk.CHUNK_SIZE / 11), position.getY() + (Chunk.CHUNK_SIZE / 11), hitboxWidth, hitboxHeight);
    }

    public Bounds getHitbox() {
        return hitbox;
    }

    public String getResistance() {
        return "NORMAL";
    }

    public String getFaiblesse() {
        return "NORMAL";
    }
}
