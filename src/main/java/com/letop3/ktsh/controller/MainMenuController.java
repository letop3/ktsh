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
    private AnchorPane exitPopupPane, creditsPopupPane;
    @FXML
    private VBox mainMenuVBox;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView backgroundImageView;
    private MainMenuView menuView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private VBox buttonContainerVBox;
    @FXML
    private Button startGameButton, settingsButton, creditsButton, exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lier la taille de l'image de fond
        backgroundImageView.fitWidthProperty().bind(mainPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(mainPane.heightProperty());

        // Lier la taille de l'image du logo
        logoImageView.fitWidthProperty().bind(mainPane.widthProperty().multiply(0.2));
        logoImageView.fitHeightProperty().bind(mainPane.heightProperty().multiply(0.2));

        // Lier la VBox interne à la taille du mainPane
        buttonContainerVBox.prefWidthProperty().bind(mainPane.widthProperty().multiply(0.5));
        buttonContainerVBox.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.4));

        // Lier la taille des boutons
        startGameButton.prefWidthProperty().bind(buttonContainerVBox.prefWidthProperty().multiply(0.8));
        startGameButton.prefHeightProperty().bind(buttonContainerVBox.prefHeightProperty().multiply(0.2));
        settingsButton.prefWidthProperty().bind(buttonContainerVBox.prefWidthProperty().multiply(0.8));
        settingsButton.prefHeightProperty().bind(buttonContainerVBox.prefHeightProperty().multiply(0.2));
        creditsButton.prefWidthProperty().bind(buttonContainerVBox.prefWidthProperty().multiply(0.8));
        creditsButton.prefHeightProperty().bind(buttonContainerVBox.prefHeightProperty().multiply(0.2));
        exitButton.prefWidthProperty().bind(buttonContainerVBox.prefWidthProperty().multiply(0.8));
        exitButton.prefHeightProperty().bind(buttonContainerVBox.prefHeightProperty().multiply(0.2));

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
        System.out.println("Exit popup shown");
        exitPopupPane.setVisible(true);
    }

    @FXML
    public void showSettings() {
        System.out.println("Settings shown");
    }

    @FXML
    public void showCredits() {
        System.out.println("Credits shown");
        creditsPopupPane.setVisible(true);
    }

    @FXML
    public void hideCredits() {
        System.out.println("Credits hidden");
        creditsPopupPane.setVisible(false);
    }

    @FXML
    public void hideExit() {
        System.out.println("Exit popup hidden");
        exitPopupPane.setVisible(false);
    }

    @FXML
    public void exitGame() {
        System.out.println("Game exited");
        Platform.exit();
        System.exit(0);
    }
}
