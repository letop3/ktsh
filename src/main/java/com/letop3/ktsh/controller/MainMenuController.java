package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.MainMenuView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable, ParentControllerInterface {
    @FXML
    private VBox exitPopupVBox, creditsPopupVBox, creditsTextVBox, creditsContentVBox;
    @FXML
    private VBox mainMenuVBox, loadingVbox;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private VBox buttonContainerVBox;
    @FXML
    private Button startGameButton, settingsButton, creditsButton, exitButton;
    @FXML
    private Button yesExitButton, noExitButton, closeCreditsButton;
    @FXML
    private Label exitLabel, creditsTitleLabel, loadingLabel;
    @FXML
    private Label credit1, credit2, credit3;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator loadingIndicator;
    @FXML
    private AnchorPane settingsInjPane;

    private boolean isPopupOpen = false;
    private MainMenuView menuView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuView = new MainMenuView();
        menuView.initializeViewComponents(exitPopupVBox, creditsPopupVBox, creditsTextVBox, creditsContentVBox, mainMenuVBox, loadingVbox,
                mainPane, backgroundImageView, logoImageView, buttonContainerVBox, startGameButton, settingsButton,
                creditsButton, exitButton, yesExitButton, noExitButton, closeCreditsButton, exitLabel,
                creditsTitleLabel, loadingLabel, credit1, credit2, credit3, progressBar, loadingIndicator);
        menuView.playMusic("src/main/resources/com/letop3/ktsh/audio/music/menu.mp3");
    }


    private Task<Void> createLoadingTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                double progress = 0;
                while (progress < 1.0) {
                    double randomIncrement = 0.2;
                    progress += randomIncrement;
                    updateProgress(progress, 1.0);
                    Thread.sleep(500);
                }
                return null;
            }
        };
    }

    @FXML
    public void startGame() {
        if (!isPopupOpen) {
            menuView.showLoadingScreen();
            Task<Void> loadingTask = createLoadingTask();
            menuView.bindProgressBar(loadingTask);
            loadingTask.setOnSucceeded(event -> {
                menuView.updateLoadingLabel("Loading Complete");
                menuView.loadNewScene();
                menuView.stopMusic();
            });

            Thread loadingThread = new Thread(loadingTask);
            loadingThread.setDaemon(true);
            loadingThread.start();
        }
    }

    @FXML
    public void showExit() {
        if (!isPopupOpen) {
            menuView.showExitPopup();
            isPopupOpen = true;
        }
    }

    @FXML
    public void showSettings() {
        menuView.hidemain();
        menuView.inject(this, settingsInjPane, "/com/letop3/ktsh/settings.fxml");
    }

    @FXML
    public void showCredits() {
        if (!isPopupOpen) {
            menuView.showCreditsPopup();
            isPopupOpen = true;
        }
    }

    @FXML
    public void hideCredits() {
        menuView.hideCreditsPopup();
        isPopupOpen = false;
    }

    @FXML
    public void hideExit() {
        menuView.hideExitPopup();
        isPopupOpen = false;
    }

    @FXML
    public void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    public void changeChild() {
        menuView.showmain();
        menuView.hideSettings(settingsInjPane);
    }
}
