package com.letop3.ktsh.model;


import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;

public class Env {
    private final Player player;

    public Env() {
        this.player = new Player(new Position(106, 106));
    }

    public Player getPlayer() {
        return player;
    }

}
