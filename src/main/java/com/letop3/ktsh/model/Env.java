package com.letop3.ktsh.model;


public class Env {
    private final Player player;

    public Env() {
        this.player = new Player(new Position(10, 10, 0, 5));
    }

    public Player getPlayer() {
        return player;
    }

}
