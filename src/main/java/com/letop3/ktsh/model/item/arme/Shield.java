package com.letop3.ktsh.model.item.arme;

public class Shield extends Arme {
    public Shield(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/shield.png");
    }
}
