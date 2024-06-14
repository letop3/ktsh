package com.letop3.ktsh.model.item.arme;

public class Excaliba extends Sword{
    public Excaliba(int id, String nom, String description, int prix) {
        super(id, nom, description, prix, 3);
        this.setIcon("/com/letop3/ktsh/images/item/excalibur.png");
    }

}
