package com.jwareconsulting.arusha.common;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocaleUtils {

    private static Locale sLocale;

    public static void setLocale(Locale locale) {
        sLocale = locale;
        if(sLocale != null) {
            Locale.setDefault(sLocale);
        }
    }

//    public static void updateConfig(ContextThemeWrapper wrapper) {
//        if(sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//
//            Configuration configuration = new Configuration();
//            configuration.setLocale(sLocale);
//            wrapper.applyOverrideConfiguration(configuration);
//
//        }
//
//    }

    public static void updateConfig(Application app, Configuration configuration) {
//        if(sLocale != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //Wrapping the configuration to avoid Activity endless loop
            Configuration config = new Configuration(configuration);
            config.locale = sLocale;
            Resources res = app.getBaseContext().getResources();
            res.updateConfiguration(config, res.getDisplayMetrics());

        //final Locale locale = BaseActivity.getLocale(this);
//        Locale.setDefault(locale);//set new locale as default
//        Configuration config = new Configuration();//get Configuration
//        config.locale = locale;//set config locale as selected locale
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
//    }
}
