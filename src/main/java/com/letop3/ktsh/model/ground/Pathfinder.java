package com.letop3.ktsh.model.ground;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

public class Pathfinder {
    private final Ground ground;
    private Position target;

    public Pathfinder(Position target, Ground ground) {
        this.target = target;
        this.ground = ground;
        calculateDistance(ground);
    }

    private void calculateDistance(Ground ground) {
        
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
        calculateDistance(ground);
    }

    public Direction directionToTarget(Position start) {
        // TODO: Implement real pathfinding
        double x = target.getX() - start.getX();
        double y = target.getY() - start.getY();

        if (x == 0 && y == 0) {
            return null;
        }

        Direction result = null;
        if (x > 0) {
            result = Direction.EAST;
        }
        else if (x < 0) {
            result = Direction.WEST;
        }

        if (y > 0) {
            if (result == null) result = Direction.SOUTH;
            else result.add(Direction.SOUTH);
        }
        else if (y < 0) {
            if (result == null) result = Direction.NORTH;
            else result.add(Direction.NORTH);
        }

        return result;
    }
}
