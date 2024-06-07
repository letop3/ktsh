package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.MainMenuView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private AnchorPane exitPopupPane;
    @FXML
    private StackPane imagePane;
    @FXML
    private ImageView backgroundImage;
    private MainMenuView menuView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImage.fitHeightProperty().bind(imagePane.heightProperty());
        backgroundImage.fitWidthProperty().bind(imagePane.widthProperty());
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
