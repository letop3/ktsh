package com.letop3.ktsh.model;


public class Env {
    private final Player player;

    public Env() {
        this.player = new Player(new Position(10, 10, 'n'));
    }

    public Player getPlayer() {
        return player;
    }

}
