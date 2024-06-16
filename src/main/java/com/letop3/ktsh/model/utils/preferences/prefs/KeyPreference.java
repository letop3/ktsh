package com.letop3.ktsh.model.utils.preferences.prefs;

import javafx.scene.input.KeyCode;

public enum KeyPreference {
    MOVE_UP("moveUp", KeyCode.Z, KeyCode.UP),
    MOVE_DOWN("moveDown", KeyCode.S, KeyCode.DOWN),
    MOVE_RIGHT("moveRight", KeyCode.D, KeyCode.RIGHT),
    MOVE_LEFT("moveLeft", KeyCode.Q, KeyCode.LEFT),
    QUICK_SLOT("quickSlot", KeyCode.A, KeyCode.DIGIT1),
    INVENTORY("inventory", KeyCode.E, KeyCode.I),
    INTERACT("interact", KeyCode.F, KeyCode.ENTER),
    ATTACK("attack", KeyCode.SPACE, KeyCode.SPACE),
    SHIELD("shield", KeyCode.SHIFT, KeyCode.SHIFT);

    public final String key;
    public final KeyCode value;
    public final KeyCode defaultValue;

    KeyPreference(String key, KeyCode value, KeyCode defaultValue) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public KeyCode getKeyCode() {
        return value;
    }

    @Override
    public String toString() {
        return key;
    }
}