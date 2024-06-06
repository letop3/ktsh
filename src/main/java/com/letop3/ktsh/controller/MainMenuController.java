package com.letop3.ktsh.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private AnchorPane imagePane;
    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backgroundImage.fitWidthProperty().bind(imagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(imagePane.heightProperty());

    }

    @FXML
    public void startGame() {
        System.out.println("Game started");
    }

    @FXML
    public void showExit() {
        System.out.println("Game exited");
    }

    @FXML
    public void showSettings() {
        System.out.println("Settings shown");
    }

    @FXML
    public void showCredits() {
        System.out.println("Credits shown");
    }
}
