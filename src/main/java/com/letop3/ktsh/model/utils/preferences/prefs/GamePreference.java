package com.letop3.ktsh.model.utils.preferences.prefs;

public interface GamePreference {
    String getKey();

    Object getDefaultValue();

    Class<?> getValueType();
}
