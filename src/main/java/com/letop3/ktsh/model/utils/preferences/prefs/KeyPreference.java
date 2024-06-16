package com.letop3.ktsh.model.utils.preferences.prefs;

import javafx.scene.input.KeyCode;

/**
 * Enum pour les préférences de touches.
 */
public enum KeyPreference implements GamePreference {
    MOVE_UP("moveUp", KeyCode.Z),
    MOVE_DOWN("moveDown", KeyCode.S),
    MOVE_RIGHT("moveRight", KeyCode.D),
    MOVE_LEFT("moveLeft", KeyCode.Q),
    QUICK_SLOT("quickSlot", KeyCode.A),
    INVENTORY("inventory", KeyCode.E),
    INTERACT("interact", KeyCode.F),
    ATTACK("attack", KeyCode.SPACE),
    SHIELD("shield", KeyCode.SHIFT);

    private final String key;
    private final KeyCode defaultValue;

    KeyPreference(String key, KeyCode defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public KeyCode getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Class<?> getValueType() {
        return KeyCode.class;
    }

    @Override
    public String toString() {
        return key;
    }
}
