package com.letop3.ktsh.model;

public class Ground {
    private final int[] GROUND = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 2, 2, 2, 2, 2, 2, 3, 1,
            1, 3, 3, 2, 2, 2, 2, 2, 2, 3, 1,
            1, 3, 2, 2, 2, 2, 2, 2, 3, 3, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1,
            1, 2, 3, 3, 2, 2, 2, 2, 2, 3, 1,
            1, 2, 2, 3, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };
    private final Player player;

    public Ground() {
        player = new Player(new Position(5, 5));
    }

    public int[] getGROUND() {
        return GROUND;
    }

    public Player getPlayer() {
        return player;
    }
}
