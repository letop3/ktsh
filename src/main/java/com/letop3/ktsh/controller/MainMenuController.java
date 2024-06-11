package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.MainMenuView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private AnchorPane exitPopupPane, creditsPopUpPane;
    @FXML
    private VBox mainMenu;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView backgroundImage;
    private MainMenuView menuView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private VBox buttonVBox;
    @FXML
    private Button button1, button2, button3, button4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lier la taille de l'image de fond
        backgroundImage.fitWidthProperty().bind(mainPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(mainPane.heightProperty());

        // Lier la taille de l'image du logo
        logoImageView.fitWidthProperty().bind(mainPane.widthProperty().multiply(0.2));
        logoImageView.fitHeightProperty().bind(mainPane.heightProperty().multiply(0.2));

        // Lier la VBox interne à la taille du mainPane
        buttonVBox.prefWidthProperty().bind(mainPane.widthProperty().multiply(0.5));
        buttonVBox.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.4));

        // Lier la taille des boutons
        button1.prefWidthProperty().bind(buttonVBox.prefWidthProperty().multiply(0.8));
        button1.prefHeightProperty().bind(buttonVBox.prefHeightProperty().multiply(0.2));
        button2.prefWidthProperty().bind(buttonVBox.prefWidthProperty().multiply(0.8));
        button2.prefHeightProperty().bind(buttonVBox.prefHeightProperty().multiply(0.2));
        button3.prefWidthProperty().bind(buttonVBox.prefWidthProperty().multiply(0.8));
        button3.prefHeightProperty().bind(buttonVBox.prefHeightProperty().multiply(0.2));
        button4.prefWidthProperty().bind(buttonVBox.prefWidthProperty().multiply(0.8));
        button4.prefHeightProperty().bind(buttonVBox.prefHeightProperty().multiply(0.2));

        menuView = new MainMenuView();
        menuView.playMusic("src/main/resources/com/letop3/ktsh/audio/music/menu.mp3");
    }

    @FXML
    public void startGame() {
        System.out.println("Game started");
        menuView.loadNewScene();
        menuView.stopMusic();
    }

    @FXML
    public void showExit() {
        System.out.println("Game exited");
        exitPopupPane.setVisible(true);
    }

    @FXML
    public void showSettings() {
        System.out.println("Settings shown");
    }

    @FXML
    public void showCredits() {
        System.out.println("Credits shown");
        creditsPopUpPane.setVisible(true);
    }

    @FXML
    public void hideCredits() {
        System.out.println("Credits hidden");
        creditsPopUpPane.setVisible(false);
    }

    @FXML
    public void hideExit() {
        System.out.println("Exit hidden");
        exitPopupPane.setVisible(false);
    }

    @FXML
    public void exitGame() {
        System.out.println("Game exited");
        Platform.exit();
        System.exit(0);
    }
}
