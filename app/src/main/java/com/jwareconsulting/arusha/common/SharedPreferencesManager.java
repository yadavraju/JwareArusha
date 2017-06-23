package com.jwareconsulting.arusha.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anjul khanal on 2/18/15.
 */
public class SharedPreferencesManager {

    Context context;
    SharedPreferences pref;
    static SharedPreferences.Editor editor;
    private static final String Locale_Preference = "Locale Preference";
    public static final String CACHE_PREFERENCE = "prefence_cache";
    public SharedPreferencesManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(AppText.APP_PREFS_NAME, 0); // 0 - for private mode
        editor = pref.edit();
    }
    public SharedPreferencesManager(Context context,String prefenceName) {
        this.context = context;
        pref = context.getSharedPreferences(prefenceName, 0); // 0 - for private mode
        editor = pref.edit();
    }



    public void saveString(String key, String stringvalue) {
        editor.putString(key, stringvalue);
        editor.commit();
    }
    public void saveFloat(String key, float floatvalue) {
        editor.putFloat(key, floatvalue);
        editor.commit();
    }
    public void saveBoolean(String key, boolean booleanvalue) {
        editor.putBoolean(key, booleanvalue);
        editor.commit();
    }
    public void saveInt(String key, int intvalue) {
        editor.putInt(key, intvalue);
        editor.commit();
    }

    public void delete(String key){
        editor.remove(key);
        editor.commit();
    }

    public void deleteAll(){
        editor.clear();
        editor.commit();
    }

    public String getString(String key){
        return pref.getString(key, "");
    }
    public boolean getBoolean(String key){
        return pref.getBoolean(key, false);
    }
}
