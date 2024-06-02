package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Stuff {
    private ObservableList<Item> inventaire;
    private Item mainG;
    private Item mainD;
    private Item quickSlot;
    public static final int TAILLE_INVENTAIRE = 12;

    public Stuff() {
        this.inventaire = FXCollections.observableArrayList();
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
     * @param itm l'item à retirer
     * @return boolean, true si l'item a été retiré, false si non.
     */
    public boolean removeItem(Item itm) {
        return this.inventaire.remove(itm);
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setMainG(int i) {
        this.mainG = this.inventaire.get(i);
    }

    public void setMainG(Item item) {
        this.mainG = item;
    }

    public void setMainG() {
        this.mainG = null;
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setMainD(int i) {
        this.mainD = this.inventaire.get(i);
    }

    public void setMainD(Item item) {
        this.mainG = item;
    }

    public void setMainD() {
        this.mainD = null;
    }

    /**
     * @param i indice de l'item se trouvant dans l'inventaire
     */
    public void setQuickSlot(int i) {
        this.quickSlot = this.inventaire.get(i);
    }

    public void setQuickSlot(Item item) {
        this.mainG = item;
    }


    public void setQuickSlot() {
        this.quickSlot = null;
    }

    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    public Item getMainG() {
        return mainG;
    }

    public Item getMainD() {
        return mainD;
    }

    public Item getQuickSlot() {
        return quickSlot;
    }

}
