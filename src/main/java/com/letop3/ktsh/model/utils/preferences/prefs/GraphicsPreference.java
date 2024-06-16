package com.letop3.ktsh.model.utils.preferences.prefs;

/**
 * Enum pour les contrôles des paramètres graphiques.
 */
public enum GraphicsPreference implements GamePreference {
    FULL_SCREEN("fullScreen", true, Boolean.class),
    START_FULL_SCREEN("startFullScreen", false, Boolean.class);

    private final String key;
    private final Object defaultValue;
    private final Class<?> valueType;

    /**
     * Constructeur pour l'énumération GraphicsPreference.
     *
     * @param key           Le nom du paramètre
     * @param defaultValue  La valeur par défaut du paramètre
     * @param valueType     Le type de la valeur du paramètre
     */
    GraphicsPreference(String key, Object defaultValue, Class<?> valueType) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
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
        return valueType;
    }

    @Override
    public String toString() {
        return key;
    }
}
