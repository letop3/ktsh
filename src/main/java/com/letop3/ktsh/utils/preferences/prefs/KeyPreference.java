/**
 * Date: 04/05/2024
 * Auteur: aagogue
 */

package com.letop3.ktsh.utils.preferences.prefs;

/**
 * Enumération pour les clés de contrôle du joueur.
 * Chaque clé a un nom de paramètre, une valeur par défaut et un type de valeur associé.
 */
public enum KeyPreference {
    MOVE_UP("moveUpKey", "W", String.class),
    MOVE_DOWN("moveDownKey", "S", String.class),
    MOVE_LEFT("moveLeftKey", "A", String.class),
    MOVE_RIGHT("moveRightKey", "D", String.class),
    QUICK_SLOT("quickSlotKey", "Q", String.class),
    INVENTORY("inventoryKey", "E", String.class),
    INTERACT("interactKey", "F", String.class),
    ATTACK("attackKey", "Space", String.class),
    SHIELD("shieldKey", "LeftShift", String.class),
    ESCAPE("escapeKey", "Esc", String.class);

    public final String setting;
    public final String defaultValue;
    public final Class<?> valueType;

    /**
     * Constructeur de l'énumération KeyPreference.
     *
     * @param setting      Nom du paramètre de la clé.
     * @param defaultValue Valeur par défaut de la clé.
     * @param valueType    Type de la valeur de la clé.
     */
    KeyPreference(String setting, String defaultValue, Class<?> valueType) {
        this.setting = setting;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
    }

    /**
     * Méthode pour obtenir le nom du paramètre de la clé sous forme de chaîne de caractères.
     *
     * @return Le nom du paramètre de la clé.
     */
    @Override
    public String toString() {
        return setting;
    }
}