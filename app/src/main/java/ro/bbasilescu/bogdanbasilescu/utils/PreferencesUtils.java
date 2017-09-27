package ro.bbasilescu.bogdanbasilescu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PreferencesUtils {
    private static final String PREFS_FILE_NAME = "PREFS_BBasilescu";

    public static void addToSharedPreferences(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!TextUtils.isEmpty(value)) {
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static String getFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        String value;
        if (preferences.contains(key)) {
            value = preferences.getString(key, "");
        } else {
            value = "";
        }
        return value;
    }

    public static void removeFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
