package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.item.Item;

import java.util.ArrayList;

public class Stuff {
    private ArrayList<Item> inventaire;
    private Item mainG;
    private Item mainD;
    private Item quickSlot;
    private static final int TAILLE_INVENTAIRE = 12;

    public Stuff() {
        this.inventaire = new ArrayList<>();
        this.mainG = null;
        this.mainD = null;
        this.quickSlot = null;
    }

    /**
     * @param itm l'item ramassé par le joueur
     * @return boolean, true si l'inventaire a la place nécessaire, false si non.
     */
    public boolean addItem(Item itm) {
        if (this.inventaire.size() < TAILLE_INVENTAIRE) {
            this.inventaire.add(itm);
            return true;
        }
        return false;
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setMainG(int i) {
        this.mainG = this.inventaire.get(i);
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setMainD(int i) {
        this.mainD = this.inventaire.get(i);
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setQuickSlot(int i) {
        this.quickSlot = this.inventaire.get(i);
    }

}
