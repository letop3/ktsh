package com.letop3.ktsh;

import com.letop3.ktsh.view.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/mainMenu.fxml")));

        Scene scene = new Scene(root);
        SceneManager.getInstance().setMainScene(scene);

        // Fix the window size
        primaryStage.setWidth(960);
        primaryStage.setHeight(539);
        primaryStage.setResizable(false);

        // Remove the title bar and borders
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Set fullscreen toggle on F11 without a message and remove ESC hint
        primaryStage.setFullScreenExitHint("");
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case F11:
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                    break;
            }
        });

        primaryStage.setTitle("Kill To Save Her");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
