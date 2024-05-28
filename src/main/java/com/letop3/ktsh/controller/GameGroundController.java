package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.player.Stuff;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.artefact.Artefact;
import com.letop3.ktsh.model.item.consomable.Consomable;
import com.letop3.ktsh.model.item.consomable.PotionHP;
import com.letop3.ktsh.view.GroundView;
import com.letop3.ktsh.view.player.PlayerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameGroundController implements Initializable {

    private Env env;
    private Stuff stuff;

    @FXML
    private TilePane gameGround;

    @FXML
    private Pane gamePlayer;
    @FXML
    private ListView<Item> inventaireListView;
    @FXML
    private Label mainGLabel;
    @FXML
    private Label mainDLabel;
    @FXML
    private Label quickSlotLabel;

    public GameGroundController() {
        this.stuff = new Stuff();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Env();
        //on s'occupe des vues
        GroundView groundView = new GroundView(env.getGround(), gameGround);
        PlayerView playerView = new PlayerView(env.getPlayer(), gamePlayer, env.getGround());

        //on s'occupe des controllers
        GameLoop gameLoopController = new GameLoop(playerView, groundView);
        ControlsController controlsController = new ControlsController(gameGround, env.getPlayer());

        gameLoopController.initialize(location, resources);
        controlsController.initialize(location, resources);

        //Test inventaire
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
        Item selectedItem = inventaireListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem instanceof Sword) {
                handleInventaireClickGeneric(selectedItem, "MainG");
            } else if (selectedItem instanceof Shield) {
                handleInventaireClickGeneric(selectedItem, "MainD");
            } else if (selectedItem instanceof Consomable || selectedItem instanceof Artefact) {
                handleInventaireClickGeneric(selectedItem, "QuickSlot");
            }
            updateLabels();
        }
    }

    private void handleInventaireClickGeneric(Item selectedItem, String slotType) {
        Item currentItem = null;
        switch (slotType) {
            case "MainG":
                currentItem = stuff.getMainG();
                stuff.setMainG(stuff.getInventaire().indexOf(selectedItem));
                break;
            case "MainD":
                currentItem = stuff.getMainD();
                stuff.setMainD(stuff.getInventaire().indexOf(selectedItem));
                break;
            case "QuickSlot":
                currentItem = stuff.getQuickSlot();
                stuff.setQuickSlot(stuff.getInventaire().indexOf(selectedItem));
                break;
        }

        stuff.removeItem(selectedItem);
        inventaireListView.getItems().remove(selectedItem);

        if (currentItem != null) {
            stuff.addItem(currentItem);
            inventaireListView.getItems().add(currentItem);
        }
    }

    private void updateLabels() {
        mainGLabel.setText(stuff.getMainG() != null ? stuff.getMainG().getNom() : "");
        mainDLabel.setText(stuff.getMainD() != null ? stuff.getMainD().getNom() : "");
        quickSlotLabel.setText(stuff.getQuickSlot() != null ? stuff.getQuickSlot().getNom() : "");
    }

    @FXML
    public void handleMainGLabelClick(MouseEvent event) {
        handleLabelClickGeneric("MainG");
    }

    @FXML
    public void handleMainDLabelClick(MouseEvent event) {
        handleLabelClickGeneric("MainD");
    }

    @FXML
    public void handleQuickSlotLabelClick(MouseEvent event) {
        handleLabelClickGeneric("QuickSlot");
    }

    private void handleLabelClickGeneric(String slotType) {
        Item currentItem = null;
        switch (slotType) {
            case "MainG":
                currentItem = stuff.getMainG();
                break;
            case "MainD":
                currentItem = stuff.getMainD();
                break;
            case "QuickSlot":
                currentItem = stuff.getQuickSlot();
                break;
        }

        if (currentItem != null && stuff.getInventaire().size() < Stuff.TAILLE_INVENTAIRE) {
            stuff.addItem(currentItem);
            inventaireListView.getItems().add(currentItem);
            switch (slotType) {
                case "MainG":
                    stuff.setMainG();
                    break;
                case "MainD":
                    stuff.setMainD();
                    break;
                case "QuickSlot":
                    stuff.setQuickSlot();
                    break;
            }
            updateLabels();
        }
    }
}