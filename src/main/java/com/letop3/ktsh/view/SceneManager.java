package com.letop3.ktsh.view;

import javafx.scene.Scene;

public class SceneManager {

    private static SceneManager instance;
    private Scene mainScene;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
