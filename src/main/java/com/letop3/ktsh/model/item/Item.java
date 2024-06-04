package com.letop3.ktsh.model.item;

public abstract class Item {
    private int id;
    private String nom;
    private String description;
    private int prix;
    private String iconPath;

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
}
