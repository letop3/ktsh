package com.letop3.ktsh.model;

import com.letop3.ktsh.model.files.JsonLoader;

import java.io.IOException;

public class Ground {
    public final int[] GROUND;
    private final String DEFAULT_GROUND_PATH = "src/main/resources/com/letop3/ktsh/ground.json";

    public Ground() {
        GROUND = loadGround();
    }

    private int[] loadGround() {
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

    private int[] loadDefaultGround() {
        return new int[] {
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

    public boolean canMoveTo(int x, int y) {
        return true;
    }
}