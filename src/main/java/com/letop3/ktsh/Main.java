package com.letop3.ktsh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/gameGround.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Kill To Save Her");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}