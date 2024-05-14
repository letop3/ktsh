/**
 * Date: 04/05/2024
 * Auteur: aagogue
 */

package com.letop3.ktsh.model.utils.preferences;

import com.letop3.ktsh.model.utils.preferences.prefs.AudioPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.GraphicsPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;

import java.util.prefs.Preferences;

/**
 * Classe de gestion des préférences du jeu.
 */
public class GamePreferences {
    private final Preferences preferences;

    /**
     * Constructeur de GamePreferences.
     * Initialise les préférences avec les valeurs par défaut si elles n'ont pas déjà été initialisées.
     */
    public GamePreferences() {
        this.preferences = Preferences.userRoot().node("com.leTop3.ktsh");
        initializePreferences();
    }

    /**
     * Initialise les préférences avec les valeurs par défaut si elles n'ont pas déjà été initialisées.
     */
    private void initializePreferences() {
        if (!preferences.getBoolean("initialized", false)) {
            setDefaultPreferences();
            preferences.putBoolean("initialized", true);
        }
    }

    /**
     * Réinitialise les préférences aux valeurs par défaut.
     */
    public void resetDefaultPreferences() {
        setDefaultPreferences();
    }

    /**
     * Définit les préférences aux valeurs par défaut.
     */
    private void setDefaultPreferences() {
        for (AudioPreference setting : AudioPreference.values()) {
            preferences.putFloat(setting.toString(), setting.defaultValue);
        }

        for (GraphicsPreference setting : GraphicsPreference.values()) {
            switch (setting.valueType.getSimpleName()) {
                case "Boolean":
                    preferences.putBoolean(setting.toString(), (Boolean) setting.defaultValue);
                    break;
                case "Integer":
                    preferences.putInt(setting.toString(), (Integer) setting.defaultValue);
                    break;
                case "String":
                    preferences.put(setting.toString(), (String) setting.defaultValue);
                    break;
            }
        }

        //for (KeyPreference setting : KeyPreference.values()) {
        //    preferences.put(setting.toString(), setting.defaultValue);
        //}
    }

    /**
     * Définit une préférence de type String.
     *
     * @param key   la clé de la préférence
     * @param value la valeur de la préférence
     */
    public void setPreference(String key, String value) {
        preferences.put(key, value);
    }

    /**
     * Récupère une préférence de type String.
     *
     * @param key          la clé de la préférence
     * @param defaultValue la valeur par défaut si la préférence n'est pas définie
     * @return la valeur de la préférence, ou la valeur par défaut si la préférence n'est pas définie
     */
    public String getPreference(String key, String defaultValue) {
        return preferences.get(key, defaultValue);
    }

    /**
     * Définit une préférence de type boolean.
     *
     * @param key   la clé de la préférence
     * @param value la valeur de la préférence
     */
    public void setPreference(String key, boolean value) {
        preferences.putBoolean(key, value);
    }

    /**
     * Récupère une préférence de type boolean.
     *
     * @param key          la clé de la préférence
     * @param defaultValue la valeur par défaut si la préférence n'est pas définie
     * @return la valeur de la préférence, ou la valeur par défaut si la préférence n'est pas définie
     */
    public boolean getBooleanPreference(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * Définit une préférence de type float.
     *
     * @param key   la clé de la préférence
     * @param value la valeur de la préférence
     */
    public void setPreference(String key, float value) {
        preferences.putFloat(key, value);
    }

    /**
     * Récupère une préférence de type float.
     *
     * @param key          la clé de la préférence
     * @param defaultValue la valeur par défaut si la préférence n'est pas définie
     * @return la valeur de la préférence, ou la valeur par défaut si la préférence n'est pas définie
     */
    public float getFloatPreference(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * Définit une préférence de type int.
     *
     * @param key   la clé de la préférence
     * @param value la valeur de la préférence
     */
    public void setPreference(String key, int value) {
        preferences.putInt(key, value);
    }

    /**
     * Récupère une préférence de type int.
     *
     * @param key          la clé de la préférence
     * @param defaultValue la valeur par défaut si la préférence n'est pas définie
     * @return la valeur de la préférence, ou la valeur par défaut si la préférence n'est pas définie
     */
    public int getIntPreference(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }
}