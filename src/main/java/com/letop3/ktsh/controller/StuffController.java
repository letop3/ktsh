package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.arme.Sword;
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
        inventaireListView.getItems().addAll(stuff.getInventaire());
    }


    @FXML
    public void handleInventaireClick(MouseEvent event) {
        // TODO modifier pour automatiser où l'item va en fonction du type (sword -> mainG, shield -> mainD, conso etc -> quickSlot)
        Item selectedItem = inventaireListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (stuff.getMainG() != null) {
                Item currentMainG = stuff.getMainG();
                stuff.setMainG(stuff.getInventaire().indexOf(selectedItem));
                stuff.removeItem(selectedItem);
                inventaireListView.getItems().remove(selectedItem);

                stuff.addItem(currentMainG);
                inventaireListView.getItems().add(currentMainG);
            } else {
                stuff.setMainG(stuff.getInventaire().indexOf(selectedItem));
                stuff.removeItem(selectedItem);
                inventaireListView.getItems().remove(selectedItem);
            }
            updateLabels();
        }
    }

    private void updateLabels() {
        mainGLabel.setText(stuff.getMainG() != null ? stuff.getMainG().getNom() : "None");
        mainDLabel.setText(stuff.getMainD() != null ? stuff.getMainD().getNom() : "None");
        quickSlotLabel.setText(stuff.getQuickSlot() != null ? stuff.getQuickSlot().getNom() : "None");
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
}
