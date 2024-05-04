/**
 * Date: 04/05/2024
 * Auteur: aagogue
 */

package com.letop3.ktsh.utils.preferences.prefs;

/**
 * Enum pour les contrôles des paramètres audios.
 */
public enum AudioPreference {
    MASTER_VOLUME("masterVolume", 0.8f, Float.class),
    MUSIC_VOLUME("musicVolume", 0.5f, Float.class),
    EFFECTS_VOLUME("effectsVolume", 0.7f, Float.class);

    public final String key;
    public final float defaultValue;
    public final Class<?> valueType;

    /**
     * Constructeur pour l'énumération `AudioPreference`.
     *
     * @param setting      La clé du paramètre audio.
     * @param defaultValue La valeur par défaut du paramètre audio.
     * @param valueType    Le type de la valeur du paramètre audio.
     */
    AudioPreference(String setting, float defaultValue, Class<?> valueType) {
        this.key = setting;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
    }

    /**
     * Retourne la clé du paramètre audio.
     *
     * @return La clé du paramètre audio.
     */
    @Override
    public String toString() {
        return key;
    }
}