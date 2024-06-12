package com.letop3.ktsh.model.item.arme;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.item.Item;

public abstract class Sword extends Item {
    private int atk;
    public Sword(int id, String nom, String description, int prix, int atk) {
        super(id, nom, description, prix);
        this.atk = atk;
    }

    public int getAtk() {
        return atk;
    }

    @Override
    public void action(Player player) {

    }
}
