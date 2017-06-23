package com.jwareconsulting.arusha.common;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by anjulkhanal on 9/5/16.
 */
public class MultiLanguageApp extends MultiDexApplication {
    String lang;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(this);
        lang = sharedPreferences.getString("language");
        if(lang!=null) {
            setLocale(this, lang);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(lang!=null) {
            setLocale(this, lang);
        }
    }

    public static void setLocale(Context context,String language_code) {
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language_code);
        res.updateConfiguration(conf, dm);


    }
}



