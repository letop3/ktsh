package com.letop3.ktsh.model.item;

import java.awt.*;

public abstract class Item {
    private int id;
    private String nom;
    private String description;
    private int prix;
    private Image icon;

    public Item(int id, String nom, String description, int prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.icon = null;
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

    @Override
    public String toString() {
        return this.nom;
    }
}
