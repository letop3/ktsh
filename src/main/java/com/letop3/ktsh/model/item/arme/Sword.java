package com.letop3.ktsh.model.item.arme;

import com.letop3.ktsh.model.entity.player.Player;

public class Sword extends Arme {
    public Sword(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        if (nom.equalsIgnoreCase("Excalibur")) {
            this.setIcon("/com/letop3/ktsh/images/item/excalibur.png");
        } else {
            this.setIcon("/com/letop3/ktsh/images/item/sword.png");
        }
    }

    @Override
    public void action(Player player) {

    }
}
