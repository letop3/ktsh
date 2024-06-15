package com.letop3.ktsh.model.item.consomable;

import com.letop3.ktsh.model.item.Item;

public abstract class Consomable extends Item {
    public Consomable(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
    }
}
