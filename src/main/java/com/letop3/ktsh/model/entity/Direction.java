package com.letop3.ktsh.model.entity;

public enum Direction {
    NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0),
    NORTH_EAST(1, 1), SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1), SOUTH_WEST(-1, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private static int sign(int x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        else
            return 0;
    }

    public static Direction resolvDirection(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (direction.x == sign(x) && direction.y == sign(y)) {
                return direction;
            }
        }
        return null;
    }

    public Direction add(Direction other) {
        if (other != null) return resolvDirection(this.x + other.x, this.y + other.y);
        return null;
    }

    public Direction sub(Direction other) {
        if (other != null) return resolvDirection(this.x - other.x, this.y - other.y);
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0;
    }
}
