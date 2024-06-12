package com.letop3.ktsh.model.item.arme;

import com.letop3.ktsh.model.entity.player.Player;

public class DulledSword extends Sword{
    public DulledSword(int id, String nom, String description, int prix) {
        super(id, nom, description, prix, 1);
        this.setIcon("/com/letop3/ktsh/images/item/sword.png");
    }

}
