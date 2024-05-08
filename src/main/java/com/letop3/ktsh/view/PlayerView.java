package com.letop3.ktsh.view;

import com.letop3.ktsh.model.Player;
import com.letop3.ktsh.utils.TilesetCutter;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerView {

    Player player;
    Pane gamePlayer;
    Image playerImage;
    ImageView playerImageView;
    TilesetCutter cutter;

    Image[] nImages = new Image[3];
    Image[] sImages = new Image[3];
    Image[] eImages = new Image[2];
    Image[] wImages = new Image[2];
    int currentImageIndex = 0;
    char lastDirection = 's';
    Point2D lastPosition;
    int moveCounter = 0;  // Compteur de déplacements
    int updateThreshold = 5; // Seuil de mise à jour pour l'animation, ajustez ce nombre selon vos besoins

    public PlayerView(Player player, Pane gamePlayer) {
        this.player = player;
        this.gamePlayer = gamePlayer;
        this.cutter = new TilesetCutter("/com/letop3/ktsh/images/player/player.png", 32);
        loadImages();
        this.playerImageView = new ImageView(sImages[0]);
        this.lastPosition = new Point2D(player.getPosition().getX(), player.getPosition().getY());
        draw();
    }

    private void loadImages() {
        nImages[0] = cutter.getTile(0);
        nImages[1] = cutter.getTile(1);
        nImages[2] = cutter.getTile(2);
        eImages[0] = cutter.getTile(3);
        eImages[1] = cutter.getTile(4);
        wImages[0] = cutter.getTile(5);
        wImages[1] = cutter.getTile(6);
        sImages[0] = cutter.getTile(7);
        sImages[1] = cutter.getTile(8);
        sImages[2] = cutter.getTile(9);
    }

    public void draw() {
        playerImageView.setLayoutX(player.getPosition().getX());
        playerImageView.setLayoutY(player.getPosition().getY());
        if (!gamePlayer.getChildren().contains(playerImageView)) {
            gamePlayer.getChildren().add(playerImageView);
        }
    }

    public void update() {
        double newX = player.getPosition().getX();
        double newY = player.getPosition().getY();

        playerImageView.setLayoutX(newX);
        playerImageView.setLayoutY(newY);

        // Vérifier si le joueur s'est déplacé
        if (lastPosition.getX() != newX || lastPosition.getY() != newY) {
            moveCounter++; // Incrémenter le compteur de déplacements
            if (moveCounter >= updateThreshold) {
                animatePlayer();
                moveCounter = 0; // Réinitialiser le compteur après l'animation
            }
            lastPosition = new Point2D(newX, newY); // Mettre à jour la dernière position connue
        }
    }

    private void animatePlayer() {
        char direction = player.getDirection();
        Image[] images = switch (direction) {
            case 'n' -> nImages;
            case 's' -> sImages;
            case 'e' -> eImages;
            case 'w' -> wImages;
            default -> nImages;
        }; // Définir par défaut vers le sud

        if (lastDirection != direction) {
            currentImageIndex = 0; // Réinitialiser l'index d'image si la direction change
            lastDirection = direction;
        } else {
            currentImageIndex = (currentImageIndex + 1) % images.length; // Faire défiler les images
        }

        playerImageView.setImage(images[currentImageIndex]);
    }
}
