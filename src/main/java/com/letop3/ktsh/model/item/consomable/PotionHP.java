package com.letop3.ktsh.model.item.consomable;

public class PotionHP extends Consomable {
    public PotionHP(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/potionhp.png");
    }
}
