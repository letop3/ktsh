package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.item.Item;

import java.util.ArrayList;

public class Stuff {
    private ArrayList<Item> inventaire;
    private Item mainG;
    private Item mainD;
    private Item quickSlot;

    public Stuff() {
        this.inventaire = new ArrayList<>();
        this.mainG = null;
        this.mainD = null;
        this.quickSlot = null;
    }

    public boolean setMainG(int) {
        this.mainG = mainG;
    }

    public boolean setMainD(Item mainD) {
        this.mainD = mainD;
    }

    public boolean setQuickSlot(Item quickSlot) {
        this.quickSlot = quickSlot;
    }

    public boolean
}
