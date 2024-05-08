package com.letop3.ktsh.model;

import com.letop3.ktsh.utils.JsonLoader;

import java.io.IOException;

/**
 * La classe Ground représente le terrain du jeu.
 * Elle charge le terrain à partir d'un fichier JSON ou utilise un terrain par défaut si le chargement échoue.
 */
public class Ground {
    // Chemin par défaut vers le fichier JSON du terrain
    private static final String DEFAULT_GROUND_PATH = "src/main/resources/com/letop3/ktsh/ground.json";
    // Le terrain chargé ou le terrain par défaut
    public static final int[] GROUND = loadGround();

    /**
     * Charge le terrain à partir du fichier JSON.
     * Si le chargement échoue pour une raison quelconque, le terrain par défaut est chargé.
     *
     * @return Le terrain chargé ou le terrain par défaut
     */
    private static int[] loadGround() {
        try (JsonLoader loader = new JsonLoader(DEFAULT_GROUND_PATH)) {
            int[] loadedGround = loader.getIntArray("ground");
            if (loadedGround != null && loadedGround.length > 0) {
                return loadedGround;
            } else {
                return loadDefaultGround();
            }
        } catch (IOException | ClassCastException e) {
            return loadDefaultGround();
        }
    }

    /**
     * Charge le terrain par défaut.
     *
     * @return Le terrain par défaut
     */
    private static int[] loadDefaultGround() {
        return new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 2, 2, 1, 1, 1, 1, 1, 1, 2, 0,
                0, 2, 2, 1, 1, 1, 1, 1, 1, 2, 0,
                0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0,
                0, 1, 2, 2, 1, 1, 1, 1, 1, 2, 0,
                0, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0,
                0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
    }
}