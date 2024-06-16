package com.letop3.ktsh.model.utils.preferences;

import com.letop3.ktsh.model.utils.preferences.prefs.AudioPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.GamePreference;
import com.letop3.ktsh.model.utils.preferences.prefs.GraphicsPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.scene.input.KeyCode;

import java.util.prefs.Preferences;

/**
 * Classe de gestion des préférences du jeu.
 */
public class GamePreferences {
    private static final Preferences preferences = Preferences.userRoot().node("com.leTop3.ktsh");

    static {
        initializePreferences();
    }

    /**
     * Initialise les préférences avec les valeurs par défaut si elles n'ont pas déjà été initialisées.
     */
    public static void initializePreferences() {
        if (!preferences.getBoolean("initialized", false)) {
            setDefaultPreferences();
            preferences.putBoolean("initialized", true);
        }
    }

    /**
     * Réinitialise les préférences aux valeurs par défaut.
     */
    public static void resetDefaultPreferences() {
        setDefaultPreferences();
    }

    /**
     * Définit les préférences aux valeurs par défaut.
     */
    private static void setDefaultPreferences() {
        setDefaultPreferences(AudioPreference.values());
        setDefaultPreferences(GraphicsPreference.values());
        setDefaultPreferences(KeyPreference.values());

        // Ajouter la préférence par défaut pour la touche de basculement en plein écran
        setKeyCodePreference("fullScreenToggle", KeyCode.F11);
    }

    private static void setDefaultPreferences(GamePreference[] preferencesArray) {
        for (GamePreference preference : preferencesArray) {
            if (preference.getValueType() == Boolean.class) {
                preferences.putBoolean(preference.getKey(), (Boolean) preference.getDefaultValue());
            } else if (preference.getValueType() == Integer.class) {
                preferences.putInt(preference.getKey(), (Integer) preference.getDefaultValue());
            } else if (preference.getValueType() == Float.class) {
                preferences.putFloat(preference.getKey(), (Float) preference.getDefaultValue());
            } else if (preference.getValueType() == String.class) {
                preferences.put(preference.getKey(), (String) preference.getDefaultValue());
            } else if (preference.getValueType() == KeyCode.class) {
                preferences.put(preference.getKey(), ((KeyCode) preference.getDefaultValue()).getName());
            }
        }
    }

    // Méthodes pour gérer les préférences de type String
    public static void setPreference(String key, String value) {
        preferences.put(key, value);
    }

    public static String getPreference(String key, String defaultValue) {
        return preferences.get(key, defaultValue);
    }

    // Méthodes pour gérer les préférences de type boolean
    public static void setPreference(String key, boolean value) {
        preferences.putBoolean(key, value);
    }

    public static boolean getBooleanPreference(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    // Méthodes pour gérer les préférences de type float
    public static void setPreference(String key, float value) {
        preferences.putFloat(key, value);
    }

    public static float getFloatPreference(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    // Méthodes pour gérer les préférences de type int
    public static void setPreference(String key, int value) {
        preferences.putInt(key, value);
    }

    public static int getIntPreference(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    // Méthodes pour gérer les préférences de type KeyCode
    public static void setKeyCodePreference(String key, KeyCode value) {
        preferences.put(key, value.getName());
    }

    public static KeyCode getKeyCodePreference(String key, KeyCode defaultValue) {
        String keyName = preferences.get(key, defaultValue.getName());
        return KeyCode.getKeyCode(keyName);
    }
}
