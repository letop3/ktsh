package com.letop3.ktsh.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TilesetCutter {
    private final Image tilesetImage;
    private final int tileSize;
    private final List<Image> tiles;

    /**
     * Constructeur de TilesetCutter qui découpe une image en tuiles de taille spécifiée.
     *
     * @param imagePath Chemin vers l'image source.
     * @param tileSize Taille des tuiles carrées à découper (en pixels).
     */
    public TilesetCutter(String imagePath, int tileSize) throws IllegalArgumentException {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de l'image ne peut pas être nul ou vide.");
        }
        if (tileSize <= 0) {
            throw new IllegalArgumentException("La taille des tuiles doit être positive.");
        }

        this.tilesetImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.tileSize = tileSize;
        this.tiles = new ArrayList<>();
        cutTiles();
    }

    /**
     * Méthode pour découper l'image en tuiles.
     * Cette méthode découpe l'image en tuiles carrées de taille spécifiée lors de la construction de l'objet.
     * Chaque tuile est ensuite ajoutée à la liste des tuiles.
     */
    private void cutTiles() {
        int numTilesX = (int) (tilesetImage.getWidth() / tileSize);
        int numTilesY = (int) (tilesetImage.getHeight() / tileSize);
        PixelReader pixelReader = tilesetImage.getPixelReader();

        for (int y = 0; y < numTilesY; y++) {
            for (int x = 0; x < numTilesX; x++) {
                Image tile = new WritableImage(pixelReader, x * tileSize, y * tileSize, tileSize, tileSize);
                tiles.add(tile);
            }
        }
    }

    /**
     * Retourne une liste immuable des tuiles découpées.
     *
     * @return Liste immuable des tuiles.
     */
    public List<Image> getTiles() {
        return tiles;
    }

    /**
     * Récupère une tuile par son indice.
     *
     * @param index Indice de la tuile à récupérer.
     * @return L'image de la tuile à l'indice spécifié.
     */
    public Image getTile(int n) {
        return tiles.get(n);
    }

    /**
     * Retourne la taille des tuiles.
     *
     * @return La taille des tuiles en pixels.
     */
    public int getTileSize() {
        return tileSize;
    }
}
