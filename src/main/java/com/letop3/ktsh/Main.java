package com.letop3.ktsh;

import com.letop3.ktsh.model.utils.preferences.GamePreferences;
import com.letop3.ktsh.model.utils.preferences.prefs.GraphicsPreference;
import com.letop3.ktsh.view.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialiser les préférences
        GamePreferences.initializePreferences();

        // Afficher toutes les préférences sauvegardées
        GamePreferences.printAllPreferences();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/mainMenu.fxml")));

        Scene scene = new Scene(root);
        SceneManager.getInstance().setMainScene(scene);

        // Fix the window size
        primaryStage.setWidth(960);
        primaryStage.setHeight(539);
        primaryStage.setResizable(false);

        // Remove the title bar and borders
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Set fullscreen toggle on user-defined key (default is F11)
        KeyCode fullScreenToggleKey = GamePreferences.getKeyCodePreference(
                GraphicsPreference.FULL_SCREEN_TOGGLE.getKey(),
                (KeyCode) GraphicsPreference.FULL_SCREEN_TOGGLE.getDefaultValue()
        );

        // Set initial fullscreen state based on preference
        boolean startFullScreen = GamePreferences.getBooleanPreference(
                GraphicsPreference.START_FULL_SCREEN.getKey(),
                (Boolean) GraphicsPreference.START_FULL_SCREEN.getDefaultValue()
        );
        primaryStage.setFullScreen(startFullScreen);

        // Set fullscreen exit hint
        primaryStage.setFullScreenExitHint("");

        // Set event handler for key presses
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == fullScreenToggleKey) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        primaryStage.setTitle("Kill To Save Her");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
