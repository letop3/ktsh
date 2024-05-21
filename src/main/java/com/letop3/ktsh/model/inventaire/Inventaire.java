package com.letop3.ktsh.model.inventaire;

import com.letop3.ktsh.model.inventaire.item.Item;

import java.util.ArrayList;

public class Inventaire {
    private final int TAILLE_BAG = 12;
    private ArrayList<Item> bag;

    public Inventaire() {
        this.bag = new ArrayList<Item>();
    }

    public ArrayList<Item> getBag() {
        return bag;
    }

    public Item getItemBag(int i) {
        return this.bag.get(i);
    }

    public boolean addItem(Item itm) {
        if (this.bag.size() < TAILLE_BAG) {
            this.bag.add(itm);
            return true;
        }
        return false;
    }

    public void removeItem(int i) {
        this.bag.remove(i);
    }
}
