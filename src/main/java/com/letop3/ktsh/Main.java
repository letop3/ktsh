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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static final long FULLSCREEN_TOGGLE_DEBOUNCE_DELAY = 500; // 500 milliseconds debounce delay
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static boolean keyRecentlyChanged = false; // Add this field
    private boolean fullscreenToggleAllowed = true;

    public static void main(String[] args) {
        launch(args);
    }

    public static void notifyKeyChange() {
        keyRecentlyChanged = true;
        scheduler.schedule(() -> keyRecentlyChanged = false, FULLSCREEN_TOGGLE_DEBOUNCE_DELAY, TimeUnit.MILLISECONDS);
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
            if (event.getCode() == GamePreferences.getKeyCodePreference(
                    GraphicsPreference.FULL_SCREEN_TOGGLE.getKey(),
                    (KeyCode) GraphicsPreference.FULL_SCREEN_TOGGLE.getDefaultValue()
            )) {
                if (fullscreenToggleAllowed && !keyRecentlyChanged) {
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                    fullscreenToggleAllowed = false;
                    scheduler.schedule(() -> fullscreenToggleAllowed = true, FULLSCREEN_TOGGLE_DEBOUNCE_DELAY, TimeUnit.MILLISECONDS);
                }
                // Reset keyRecentlyChanged after debounce delay
                keyRecentlyChanged = false;
            }
        });

        primaryStage.setTitle("Kill To Save Her");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        scheduler.shutdownNow();
        super.stop();
    }
}
