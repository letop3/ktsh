package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.artefact.Artefact;
import com.letop3.ktsh.model.item.consomable.Consomable;
import com.letop3.ktsh.model.item.consomable.PotionHP;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class StuffController {

    @FXML
    private ListView<Item> inventaireListView;

    @FXML
    private Label mainGLabel;

    @FXML
    private Label mainDLabel;

    @FXML
    private Label quickSlotLabel;

    private Stuff stuff;

    public StuffController() {
        this.stuff = new Stuff();
    }

    @FXML
    public void initialize() {
        Item sword = new Sword(1, "Sword Test", "Un test pour arme", 100);
        stuff.addItem(sword);
        Item sword2 = new Sword(2, "Sword Test2", "Un test pour arme", 100);
        stuff.addItem(sword2);
        Item shield = new Shield(1, "Shield Test", "Un test pour bouclier", 100);
        stuff.addItem(shield);
        Item potionHP = new PotionHP(1, "Potion Test Conso", "Un test pour conso", 100);
        stuff.addItem(potionHP);
        inventaireListView.getItems().addAll(stuff.getInventaire());
    }


    @FXML
    public void handleInventaireClick(MouseEvent event) {
        // TODO modifier pour automatiser où l'item va en fonction du type (sword -> mainG, shield -> mainD, conso etc -> quickSlot)
        Item selectedItem = inventaireListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem instanceof Sword) {
                handleInventaireClickMainG(selectedItem);
            } else if (selectedItem instanceof Shield) {
                handleInventaireClickMainD(selectedItem);
            } else if (selectedItem instanceof Consomable || selectedItem instanceof Artefact) {
                handleInventaireClickQuickSlot(selectedItem);
            }
            updateLabels();
        }
    }


    private void handleInventaireClickMainG(Item selectedItem) {
        if (stuff.getMainG() != null) {
            Item currentMainG = stuff.getMainG();
            stuff.setQuickSlot(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);

            stuff.addItem(currentMainG);
            inventaireListView.getItems().add(currentMainG);
        } else {
            stuff.setMainG(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);
        }
    }

    private void handleInventaireClickMainD(Item selectedItem) {
        if (stuff.getMainD() != null) {
            Item currentMainD = stuff.getMainD();
            stuff.setMainD(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);

            stuff.addItem(currentMainD);
            inventaireListView.getItems().add(currentMainD);
        } else {
            stuff.setMainD(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);
        }
    }

    private void handleInventaireClickQuickSlot(Item selectedItem) {
        if (stuff.getQuickSlot() != null) {
            Item currentQuickSlot = stuff.getQuickSlot();
            stuff.setQuickSlot(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);

            stuff.addItem(currentQuickSlot);
            inventaireListView.getItems().add(currentQuickSlot);
        } else {
            stuff.setQuickSlot(stuff.getInventaire().indexOf(selectedItem));
            stuff.removeItem(selectedItem);
            inventaireListView.getItems().remove(selectedItem);
        }
    }

    private void updateLabels() {
        mainGLabel.setText(stuff.getMainG() != null ? stuff.getMainG().getNom() : "");
        mainDLabel.setText(stuff.getMainD() != null ? stuff.getMainD().getNom() : "");
        quickSlotLabel.setText(stuff.getQuickSlot() != null ? stuff.getQuickSlot().getNom() : "");
    }

    @FXML
    public void handleMainGLabelClick(MouseEvent event) {
        if (stuff.getMainG() != null && stuff.getInventaire().size() < Stuff.TAILLE_INVENTAIRE) {
            stuff.addItem(stuff.getMainG());
            inventaireListView.getItems().add(stuff.getMainG());
            stuff.setMainG();
            updateLabels();
        }
    }

    @FXML
    public void handleMainDLabelClick(MouseEvent event) {
        if (stuff.getMainD() != null && stuff.getInventaire().size() < Stuff.TAILLE_INVENTAIRE) {
            stuff.addItem(stuff.getMainD());
            inventaireListView.getItems().add(stuff.getMainD());
            stuff.setMainD();
            updateLabels();
        }
    }

    @FXML
    public void handleQuickSlotLabelClick(MouseEvent event) {
        if (stuff.getQuickSlot() != null && stuff.getInventaire().size() < Stuff.TAILLE_INVENTAIRE) {
            stuff.addItem(stuff.getQuickSlot());
            inventaireListView.getItems().add(stuff.getQuickSlot());
            stuff.setQuickSlot();
            updateLabels();
        }
    }
}
