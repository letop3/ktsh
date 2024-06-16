package com.letop3.ktsh.model.utils.preferences.prefs;

/**
 * Enum pour les contrôles des paramètres audios.
 */
public enum AudioPreference implements GamePreference {
    MASTER_VOLUME("masterVolume", 0.8f),
    MUSIC_VOLUME("musicVolume", 0.5f),
    EFFECTS_VOLUME("effectsVolume", 0.7f);

    private final String key;
    private final float defaultValue;

    /**
     * Constructeur pour l'énumération `AudioPreference`.
     *
     * @param key           La clé du paramètre audio.
     * @param defaultValue  La valeur par défaut du paramètre audio.
     */
    AudioPreference(String key, float defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Class<?> getValueType() {
        return Float.class;
    }

    @Override
    public String toString() {
        return key;
    }
}
