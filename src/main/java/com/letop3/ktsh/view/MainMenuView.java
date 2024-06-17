package com.letop3.ktsh.view;

import com.letop3.ktsh.controller.ParentControllerInterface;
import com.letop3.ktsh.controller.SettingsController;
import com.letop3.ktsh.view.music.SoundPlayer;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class MainMenuView {

    private VBox exitPopupVBox, creditsPopupVBox, creditsTextVBox, creditsContentVBox;
    private VBox mainMenuVBox, loadingVbox;
    private StackPane mainPane;
    private ImageView backgroundImageView, logoImageView;
    private VBox buttonContainerVBox;
    private Button startGameButton, settingsButton, creditsButton, exitButton;
    private Button yesExitButton, noExitButton, closeCreditsButton;
    private Label exitLabel, creditsTitleLabel, loadingLabel;
    private Label credit1, credit2, credit3;
    private ProgressBar progressBar;
    private AnchorPane settingsInjPane;

    public void initializeViewComponents(VBox exitPopupVBox, VBox creditsPopupVBox, VBox creditsTextVBox, VBox creditsContentVBox,
                                         VBox mainMenuVBox, VBox loadingVbox, StackPane mainPane, ImageView backgroundImageView,
                                         ImageView logoImageView, VBox buttonContainerVBox, Button startGameButton, Button settingsButton,
                                         Button creditsButton, Button exitButton, Button yesExitButton, Button noExitButton, Button closeCreditsButton,
                                         Label exitLabel, Label creditsTitleLabel, Label loadingLabel, Label credit1, Label credit2, Label credit3,
                                         ProgressBar progressBar, ProgressIndicator loadingIndicator, AnchorPane settingsInjPane) {

        // Initialisation des composants
        this.exitPopupVBox = exitPopupVBox;
        this.creditsPopupVBox = creditsPopupVBox;
        this.creditsTextVBox = creditsTextVBox;
        this.creditsContentVBox = creditsContentVBox;
        this.mainMenuVBox = mainMenuVBox;
        this.loadingVbox = loadingVbox;
        this.mainPane = mainPane;
        this.backgroundImageView = backgroundImageView;
        this.logoImageView = logoImageView;
        this.buttonContainerVBox = buttonContainerVBox;
        this.startGameButton = startGameButton;
        this.settingsButton = settingsButton;
        this.creditsButton = creditsButton;
        this.exitButton = exitButton;
        this.yesExitButton = yesExitButton;
        this.noExitButton = noExitButton;
        this.closeCreditsButton = closeCreditsButton;
        this.exitLabel = exitLabel;
        this.creditsTitleLabel = creditsTitleLabel;
        this.loadingLabel = loadingLabel;
        this.credit1 = credit1;
        this.credit2 = credit2;
        this.credit3 = credit3;
        this.progressBar = progressBar;
        this.settingsInjPane = settingsInjPane;

        // Appel de la méthode de liaison des composants
        bindUIComponents();
    }

    private void bindUIComponents() {
        // Liaison des propriétés des composants
        bindImageView(backgroundImageView, mainPane);
        bindLogoView(logoImageView, mainPane);
        bindButtonContainer(buttonContainerVBox, mainPane);
        bindButtons(buttonContainerVBox, startGameButton, settingsButton, creditsButton, exitButton);
        bindPopups(exitPopupVBox, creditsPopupVBox);
        bindExitPopupComponents(yesExitButton, noExitButton, exitLabel, exitPopupVBox);
        bindCreditsPopupComponents(closeCreditsButton, creditsTitleLabel, creditsContentVBox, creditsPopupVBox, creditsTextVBox);
        bindTextStyles();
        bindProgressBar(loadingLabel, progressBar, mainPane);
    }

    private void bindImageView(ImageView imageView, StackPane pane) {
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
    }

    private void bindLogoView(ImageView logoView, StackPane pane) {
        logoView.fitWidthProperty().bind(pane.widthProperty().multiply(0.2));
        logoView.fitHeightProperty().bind(pane.heightProperty().multiply(0.2));
    }

    private void bindButtonContainer(VBox container, StackPane pane) {
        container.prefWidthProperty().bind(pane.widthProperty().multiply(0.5));
        container.prefHeightProperty().bind(pane.heightProperty().multiply(0.4));
    }

    private void bindButtons(VBox container, Button... buttons) {
        for (Button button : buttons) {
            button.prefWidthProperty().bind(container.prefWidthProperty().multiply(0.8));
            button.prefHeightProperty().bind(container.prefHeightProperty().multiply(0.2));
        }
    }

    private void bindPopups(VBox... popups) {
        for (VBox popup : popups) {
            popup.prefWidthProperty().bind(mainPane.widthProperty().multiply(0.5));
            popup.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.4));
            popup.maxWidthProperty().setValue(800);
            popup.maxHeightProperty().setValue(400);
        }
    }

    private void bindExitPopupComponents(Button yesButton, Button noButton, Label label, VBox popup) {
        yesButton.prefWidthProperty().bind(popup.prefWidthProperty().multiply(0.3));
        yesButton.prefHeightProperty().bind(popup.prefHeightProperty().multiply(0.2));
        noButton.prefWidthProperty().bind(popup.prefWidthProperty().multiply(0.3));
        noButton.prefHeightProperty().bind(popup.prefHeightProperty().multiply(0.2));
        label.prefWidthProperty().bind(popup.prefWidthProperty().multiply(0.8));
        label.prefHeightProperty().bind(popup.prefHeightProperty().multiply(0.2));
    }

    private void bindCreditsPopupComponents(Button closeButton, Label titleLabel, VBox contentVBox, VBox popupVBox, VBox textVBox) {
        closeButton.prefWidthProperty().bind(popupVBox.prefWidthProperty().multiply(0.3));
        closeButton.prefHeightProperty().bind(popupVBox.prefHeightProperty().multiply(0.2));
        titleLabel.prefWidthProperty().bind(popupVBox.prefWidthProperty().multiply(0.8));
        titleLabel.prefHeightProperty().bind(popupVBox.prefHeightProperty().multiply(0.2));
        contentVBox.prefWidthProperty().bind(popupVBox.prefWidthProperty().multiply(0.8));
        contentVBox.prefHeightProperty().bind(popupVBox.prefHeightProperty().multiply(0.6));
        textVBox.prefWidthProperty().bind(contentVBox.prefWidthProperty());
        textVBox.prefHeightProperty().bind(contentVBox.prefHeightProperty().multiply(0.6));
    }

    private void bindTextStyles() {
        exitLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.02).asString(), "px;"));
        startGameButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        settingsButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        creditsButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        exitButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        creditsTitleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.03).asString(), "px;"));
        credit1.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        credit2.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
        credit3.styleProperty().bind(Bindings.concat("-fx-font-size: ", mainPane.widthProperty().multiply(0.015).asString(), "px;"));
    }

    private void bindProgressBar(Label loadingLabel, ProgressBar progressBar, StackPane pane) {
        progressBar.prefWidthProperty().bind(pane.widthProperty().multiply(0.6));
        progressBar.prefHeightProperty().bind(pane.heightProperty().multiply(0.05));
        loadingLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", pane.widthProperty().multiply(0.02).asString(), "px;"));
    }

    public void hidemain() {
        mainMenuVBox.setVisible(false);
    }

    public void showmain() {
        mainMenuVBox.setVisible(true);
    }

    public void hideSettings() {
        settingsInjPane.getChildren().clear();
        settingsInjPane.setVisible(false);
    }

    public void loadNewScene() {
        try {
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/gameGround.fxml")));
            Scene scene = SceneManager.getInstance().getMainScene();
            scene.setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String musicFile) {
        SoundPlayer.playBackgroundMusic(musicFile);
    }

    public void stopMusic() {
        SoundPlayer.stopBackgroundMusic();
    }

    public void showExitPopup() {
        exitPopupVBox.setVisible(true);
    }

    public void hideExitPopup() {
        exitPopupVBox.setVisible(false);
    }

    public void showCreditsPopup() {
        creditsPopupVBox.setVisible(true);
    }

    public void hideCreditsPopup() {
        creditsPopupVBox.setVisible(false);
    }

    public void showLoadingScreen() {
        mainMenuVBox.setVisible(false);
        loadingVbox.setVisible(true);
    }

    public void hideLoadingScreen() {
        loadingVbox.setVisible(false);
        mainMenuVBox.setVisible(true);
    }

    public void bindProgressBar(Task<Void> task) {
        progressBar.progressProperty().bind(task.progressProperty());
    }

    public void updateLoadingLabel(String text) {
        loadingLabel.setText(text);
    }

    public void inject(ParentControllerInterface controller, String path) {
        try {
            settingsInjPane.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane newPane = loader.load();
            SettingsController settingsController = loader.getController();
            settingsController.setParentController(controller);
            settingsInjPane.getChildren().add(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
