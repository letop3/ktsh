package com.letop3.ktsh.model;

public class Player {
    private final Position position;

    public Player(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                '}';
    }

}