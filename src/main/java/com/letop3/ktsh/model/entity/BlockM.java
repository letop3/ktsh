package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.ground.Ground;
import java.util.ArrayList;

public class BlockM extends Entity {

    private ArrayList<Direction> directions;
    private double distanceParcouru;
    private static final double MAX_DISTANCE = 32;

    public BlockM(Position position, Ground ground, ArrayList<Direction> directions) {
        super(position, ground);
        this.directions = directions;
        this.distanceParcouru = 0;
    }

    @Override
    public void setDirection(Direction direction) {
        if (directions.contains(direction)) {
            super.setDirection(direction);
            this.distanceParcouru = 0;
        }
    }

    @Override
    public void update(long frame) {
        if (distanceParcouru < MAX_DISTANCE) {
            double xOrigin = getPosition().getX();
            double yOrigin = getPosition().getY();

            super.update(frame);

            double xNew = getPosition().getX();
            double yNew = getPosition().getY();

            double parcouru = Math.sqrt(Math.pow(xNew - xOrigin, 2) + Math.pow(yNew - yOrigin, 2));
            distanceParcouru += parcouru;

            if (distanceParcouru >= MAX_DISTANCE) {
                stop();
            }
        }
    }

    private void stop() {
        setDirection(null);
    }
}
