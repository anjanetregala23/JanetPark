package com.parku.au.janetpark.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Celdane.Lansangan on 2/18/2016.
 */
public class SharedPrefManager {
    private static final String PREFS_NAME = "au.com.PARKU_PREFS";

    private SharedPreferences preferences;

    public SharedPrefManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        if(preferences != null) {
            return preferences;
        }

        throw new RuntimeException("Prefs class not correctly instantiated.");
    }

    /**
     * Retrieves a boolean value
     * @param key The name of the preference key
     * @param value The value to return if the preference does not exist
     * @return Returns the preference if it exists or the parameter value
     */
    public boolean getBoolean(final String key, final boolean value) {
        return getPreferences().getBoolean(key, value);
    }

    /**
     * Stores a boolean value
     * @param key The name of the preference key
     * @param value The new value for the preference
     */
    public void putBoolean(final String key, final boolean value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullKey(value);
        preferences.edit().putString(key, value).apply();
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param key String
     */
    public void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
}
