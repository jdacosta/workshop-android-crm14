package fr.gobelins.crm14.workshop_android_crm14.services.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String SHARED_PREFERENCES = "fr.gobelins.crm14.workshop_android_crm14";
    public static final String RSA_PUBLIC_KEY = "RSA_PUBLIC_KEY";
    public static final String RSA_PRIVATE_KEY = "RSA_PRIVATE_KEY";
    public static SharedPreferences mPreferences;

    public static void init(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean clear() {
        return mPreferences.edit().clear().commit();
    }

    public static boolean remove(String key) {
        return mPreferences.edit().remove(key).commit();
    }

    public static boolean putBoolean(String key, boolean bool) {
        return mPreferences.edit().putBoolean(key, bool).commit();
    }

    public static boolean putString(String key, String s) {
        return mPreferences.edit().putString(key, s).commit();
    }

    public static boolean putInteger(String key, Integer integer) {
        return mPreferences.edit().putInt(key, integer).commit();
    }

    public static boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    public static int getInteger(String key) {
        return mPreferences.getInt(key, 0);
    }

    public static String getString(String key) {
        return mPreferences.getString(key, null);
    }
}