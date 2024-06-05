package com.letop3.ktsh.model.item.consomable;

import com.letop3.ktsh.model.entity.player.Player;

public class PotionHP extends Consomable {
    public PotionHP(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/potionhp.png");
    }

    @Override
    public void action(Player player) {
        player.setHp(Math.min(player.getHp()+4, player.getMaxHp()));
    }
}
