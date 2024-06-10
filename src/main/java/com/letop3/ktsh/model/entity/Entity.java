package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.Updatable;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class Entity implements Updatable {
    protected IntegerProperty hp;
    private final static double speed = 2;
    private final Ground ground;
    private final Position position;
    private Direction direction;
    private Direction lastDirection;

    private double hitboxWidth, hitboxHeight;
    private Bounds hitbox;

    public Entity(Position position, Ground ground) {
        this.position = position;
        this.direction = null;
        this.ground = ground;
        this.lastDirection = Direction.SOUTH;

        this.hitboxWidth = 50.0;
        this.hitboxHeight = 52.0;
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
            return ground.getFinalPositionAfterCollision(position.getX(), position.getY(), direction, speed, this instanceof Player);
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

    public void takeDamage(int dmg) {
        this.hp.set(Math.max(this.hp.get() - dmg, 0));
    }

    public int getHp(){
        return hp.get();
    }

    public void setHitboxSize(double width, double height) {
        hitboxWidth = width;
        hitboxHeight = height;
    }

    public Bounds getHitbox() {
        return hitbox;
    }
}
