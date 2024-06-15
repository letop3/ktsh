package com.letop3.ktsh.view;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LoadingView {
    private final int MINMS = 25;
    private final int MAXMS = 200;

    private ProgressBar progressBar;
    private Label messageLabel;
    private Circle loadingCircle;
    private Random rand;

    private final List<String> messages = Arrays.asList(
            "Initialisation des paramètres...",
            "Chargement des ressources...",
            "Connexion au serveur...",
            "Téléchargement des données...",
            "Configuration de l'interface...",
            "Vérification des mises à jour...",
            "Mise en place des modules...",
            "Analyse des données...",
            "Optimisation des performances...",
            "Préparation de l'environnement...",
            "Assemblage des composants...",
            "Finalisation de l'installation...",
            "Chargement des fichiers temporaires...",
            "Décompression des archives...",
            "Synchronisation avec le cloud...",
            "Vérification de l'intégrité des données...",
            "Mise à jour des dépendances...",
            "Enregistrement des préférences utilisateur...",
            "Démarrage des services en arrière-plan...",
            "Nettoyage des fichiers temporaires..."
    );

    public LoadingView(ProgressBar progressBar, Label messageLabel, Circle loadingCircle) {
        this.progressBar = progressBar;
        this.messageLabel = messageLabel;
        this.loadingCircle = loadingCircle;
        this.rand = new Random();
    }

    public void startLoading() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), loadingCircle);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.play();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(rand.nextInt(MINMS, MAXMS)); // Simule le temps de chargement
                    updateProgress(i, 100);
                    if (i % 5 == 0) { // Change le message toutes les 5% de progression
                        updateMessage(messages.get(rand.nextInt(messages.size())));
                    }
                }
                updateMessage("Chargement terminé !");
                Thread.sleep(100);
                Platform.runLater(() -> loadNewScene());
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        messageLabel.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    public void loadNewScene() {
        try {
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/letop3/ktsh/gameGround.fxml")));
            Scene scene = SceneManager.getInstance().getMainScene();
            Platform.runLater(() -> scene.setRoot(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
