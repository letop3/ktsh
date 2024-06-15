/**
 * Date: 04/05/2024
 * Auteur: aagogue
 */


package com.letop3.ktsh.model.utils.preferences.prefs;

/**
 * Enum pour les contrôles des paramètres graphiques.
 */
public enum GraphicsPreference {
    FULL_SCREEN("fullScreen", true, Boolean.class),
    RESOLUTION_WIDTH("resolutionWidth", 1920, Integer.class),
    RESOLUTION_HEIGHT("resolutionHeight", 1080, Integer.class);

    public final String setting;
    public final Object defaultValue;
    public final Class<?> valueType;

    /**
     * Constructeur pour l'énumération GraphicsPreference.
     *
     * @param setting      Le nom du paramètre
     * @param defaultValue La valeur par défaut du paramètre
     * @param valueType    Le type de la valeur du paramètre
     */
    GraphicsPreference(String setting, Object defaultValue, Class<?> valueType) {
        this.setting = setting;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
    }

    /**
     * Méthode pour obtenir une représentation sous forme de chaîne de l'énumération.
     *
     * @return Le nom du paramètre
     */
    @Override
    public String toString() {
        return setting;
    }
}