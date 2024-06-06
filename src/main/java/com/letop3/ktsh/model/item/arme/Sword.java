package com.letop3.ktsh.model.item.arme;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.item.Item;

public abstract class Sword extends Item {
    public Sword(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
    }

    @Override
    public void action(Player player) {

    }
}
