package com.letop3.ktsh.model.item;

import com.letop3.ktsh.model.entity.player.Player;

public abstract class Item {
    private int id;
    private String nom;
    private String description;
    private int prix;
    private String iconPath;
    private boolean isOnCD;
    private long cooldown;

    public Item(int id, String nom, String description, int prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.iconPath = null;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }

    public void setIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public String getIconPath() {
        return iconPath;
    }

    public abstract void action(Player player);

    public boolean isOnCD() {
        return isOnCD;
    }

    public void setOnCD(boolean onCD) {
        isOnCD = onCD;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
