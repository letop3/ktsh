package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.MainMenuView;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private VBox exitPopupVBox, creditsPopupVBox, creditsTextVBox, creditsContentVBox;
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
    @FXML
    private Button yesExitButton, noExitButton, closeCreditsButton;
    @FXML
    private Label exitLabel, creditsTitleLabel;
    @FXML
    private Label credit1, credit2, credit3;

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

        // Lier la taille et la position des popups avec des limites maximales
        exitPopupVBox.prefWidthProperty().bind(mainPane.widthProperty().multiply(0.5));
        exitPopupVBox.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.4));
        exitPopupVBox.maxWidthProperty().setValue(800);  // Valeur maximale de la largeur
        exitPopupVBox.maxHeightProperty().setValue(400); // Valeur maximale de la hauteur

        creditsPopupVBox.prefWidthProperty().bind(mainPane.widthProperty().multiply(0.5));
        creditsPopupVBox.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.4));
        creditsPopupVBox.maxWidthProperty().setValue(1000);  // Valeur maximale de la largeur
        creditsPopupVBox.maxHeightProperty().setValue(600); // Valeur maximale de la hauteur

        // Lier la taille des boutons et du label dans exitPopupVBox
        yesExitButton.prefWidthProperty().bind(exitPopupVBox.prefWidthProperty().multiply(0.3));
        yesExitButton.prefHeightProperty().bind(exitPopupVBox.prefHeightProperty().multiply(0.2));
        noExitButton.prefWidthProperty().bind(exitPopupVBox.prefWidthProperty().multiply(0.3));
        noExitButton.prefHeightProperty().bind(exitPopupVBox.prefHeightProperty().multiply(0.2));
        exitLabel.prefWidthProperty().bind(exitPopupVBox.prefWidthProperty().multiply(0.8));
        exitLabel.prefHeightProperty().bind(exitPopupVBox.prefHeightProperty().multiply(0.2));

        // Lier la taille et la position des éléments dans creditsPopupVBox
        closeCreditsButton.prefWidthProperty().bind(creditsPopupVBox.prefWidthProperty().multiply(0.3));
        closeCreditsButton.prefHeightProperty().bind(creditsPopupVBox.prefHeightProperty().multiply(0.2));
        creditsTitleLabel.prefWidthProperty().bind(creditsPopupVBox.prefWidthProperty().multiply(0.8));
        creditsTitleLabel.prefHeightProperty().bind(creditsPopupVBox.prefHeightProperty().multiply(0.2));
        creditsContentVBox.prefWidthProperty().bind(creditsPopupVBox.prefWidthProperty().multiply(0.8));
        creditsContentVBox.prefHeightProperty().bind(creditsPopupVBox.prefHeightProperty().multiply(0.6));
        creditsTextVBox.prefWidthProperty().bind(creditsContentVBox.prefWidthProperty());
        creditsTextVBox.prefHeightProperty().bind(creditsContentVBox.prefHeightProperty().multiply(0.6));

        // Lier la taille de la police des labels
        exitLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.02).asString(), "px;"));
        startGameButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        settingsButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        creditsButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        exitButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));

        creditsTitleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.03).asString(), "px;"));
        credit1.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        credit2.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        credit3.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));

        menuView = new MainMenuView();
        menuView.playMusic("src/main/resources/com/letop3/ktsh/audio/music/menu3.mp3");
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
        exitPopupVBox.setVisible(true);
    }

    @FXML
    public void showSettings() {
        System.out.println("Settings shown");
    }

    @FXML
    public void showCredits() {
        System.out.println("Credits shown");
        creditsPopupVBox.setVisible(true);
    }

    @FXML
    public void hideCredits() {
        System.out.println("Credits hidden");
        creditsPopupVBox.setVisible(false);
    }

    @FXML
    public void hideExit() {
        System.out.println("Exit popup hidden");
        exitPopupVBox.setVisible(false);
    }

    @FXML
    public void exitGame() {
        System.out.println("Game exited");
        Platform.exit();
        System.exit(0);
    }
}
