package com.jwareconsulting.arusha.common;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.mvp.presenter.LandingActivityPresenter;
import com.jwareconsulting.arusha.ui.activity.MainActivity;


/**
 * Created by anjulkhanal on 6/15/16.
 */
public class PrefenceUtil {

    private static final String TAG = PrefenceUtil.class.getSimpleName();
    private static final String KEY_EVENT_DATA = "key_event_data";
    private static final String KEY_HAS_EVENT_DATA = "key_has_event_data";

    private static final String KEY_EXCHANGE_RATE_DATA = "key_exchange_rate_data";
    private static final String KEY_HAS_EXCHANGE_RATE_DATA = "key_has_exchange_rate_data";
    private static final String KEY_TOP_THING_TO_DATA_DATA = "key_top_thing_to_do";
    private static final String KEY_HAS_TOP_THING_TO_DATA_DATA = "key_has_top_thing_to_do";
    private static final String KEY_DERECTORY_DETAIL_DATA = "key_directory_detail_data";
    private static final String KEY_HAS_DERECTORY_DETAIL_DATA = "key_has_directory_detail_data";
    public static final String KEY_SYNC_SERVICE_PROVIDER_BY_CATAGORY_SHOWALL_LIST = "key_sync_service_provider_showall_list";
    public static final String KEY_SYNC_SERVICE_PROVIDER_BY_SUB_CATAGORY = "key_sync_service_provider_by_sub_catagory";
    private static final String KEY_DERECTORY_SUBCATAGORY_DATA = "key_directory_sub_category";
    private static final String KEY_HAS_DERECTORY_SUBCATAGORY_DATA = "key_has_directory_sub_category";
    public static String KEY_SYNC_EXCHANGE_RATE = "key_sync_exchange_rate";
    public static String KEY_SYNC_SERVICE_PROVIDER_MAP = "key_service_provider_catagory_map";
    public static String KEY_SYNC_SERVICE_PROVIDER_BY_CATAGORY = "key_service_provider_catagory_by_catagory";
    public static String KEY_SYNC_ALL_SERVICE_PROVIDER_BY_CATAGORY = "key_all_service_provider_catagory";
    public static String KEY_SYNC_EVENT_LIST = "key_event_list";
    public static String KEY_SYNC_THINGS_TODO = "key_things_todo";
    public static String KEY_SYNC_URL_COSUTM_ADD = "key_sync_url_costum_add";
    static String KEY_SERVICE_DIRECTORY = "key_service_providers_data";
    static String KEY_HAS_SERVICE_DIRECTORY = "key_has_providers_data";
    static String KEY_HAS_SERVICE_PROVIDER = "key_has_map_service_providers";
    static String KEY_SERVICE_PROVIDER = "key_map_service_providers";

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void storeLastUpdate(Context context, String key, String value) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context, SharedPreferencesManager.CACHE_PREFERENCE);
        AppLog.d(TAG, "last updated value for the key" + key + "  " + "  " + "is " + value);
        preferencesManager.saveString(key, value);

    }

    public static String getLastUpdatedDate(Context context, String key) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context, SharedPreferencesManager.CACHE_PREFERENCE);
        String lastupdate = preferencesManager.getString(key);
        AppLog.i(TAG, "lastupdate key is " + lastupdate);
        return lastupdate;
    }


    /**
     * @param context
     * @return true if it has service provider data
     */
    public static boolean hasDirectoryData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getBoolean(KEY_HAS_SERVICE_DIRECTORY);
    }

    /**
     * @param json    Response Json from APi which need to be stored
     * @param context
     */
    public static void setDirectoryData(Context context, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_SERVICE_DIRECTORY, json);
        preferencesManager.saveBoolean(KEY_HAS_SERVICE_DIRECTORY, true);
    }

    public static String getDirectoryData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_SERVICE_DIRECTORY);
    }

    /**
     * @param context
     * @return json for service provider
     */
    public static String getDirectorData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_SERVICE_DIRECTORY);
    }

    /**
     * @param json    Response Json from APi which need to be stored
     * @param context
     */
    public static void setEventData(Context context, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_EVENT_DATA, json);
        preferencesManager.saveBoolean(KEY_HAS_EVENT_DATA, true);
    }

    /**
     * @param context
     * @return json for service provider
     */
    public static String getEventData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_EVENT_DATA);
    }
/*************************************************************************************************************************************/

    /**
     * @param context
     * @return true if it has service provider data
     */
    public static boolean hasMapServiceProvider(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getBoolean(KEY_HAS_SERVICE_PROVIDER);
    }

    /**
     * @param json    Response Json from APi which need to be stored
     * @param context
     */
    public static void setMapServiceProvider(Context context, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_SERVICE_PROVIDER, json);
        preferencesManager.saveBoolean(KEY_HAS_SERVICE_PROVIDER, true);
    }

    /**
     * @param context
     * @return json for service provider
     */
    public static String getMapServiceProvider(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_SERVICE_PROVIDER);
    }

    public static void storeExchageRateData(AppCompatActivity activity, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(activity);
        preferencesManager.saveString(KEY_EXCHANGE_RATE_DATA, json);
        preferencesManager.saveBoolean(KEY_HAS_EXCHANGE_RATE_DATA, true);

    }

    public static String getExchangeRateData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_EXCHANGE_RATE_DATA);
    }

    /**
     * @param json    Response Json from APi which need to be stored
     * @param context
     */
    public static void setThingToDoData(Context context, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_TOP_THING_TO_DATA_DATA, json);
        preferencesManager.saveBoolean(KEY_HAS_TOP_THING_TO_DATA_DATA, true);
    }

    /**
     * @param context
     * @return json for service provider
     */
    public static String getThingsToDoData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_TOP_THING_TO_DATA_DATA);
    }

    /**
     * @param json    Response Json from APi which need to be stored
     * @param context
     */
    public static void setDirectoryDetailData(Context context, String json, String catagoryId) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_DERECTORY_DETAIL_DATA + catagoryId, json);
        preferencesManager.saveBoolean(KEY_HAS_DERECTORY_DETAIL_DATA + catagoryId, true);
    }

    /**
     * @param context
     * @return json for service provider
     */
    public static String getDirectoryDetailData(Context context, String catagoryId) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_DERECTORY_DETAIL_DATA + catagoryId);
    }

    public static String getDirectorySubCategoryData(Context context) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        return preferencesManager.getString(KEY_DERECTORY_SUBCATAGORY_DATA);
    }

    public static void setDirectorySubCategorylData(Context context, String json) {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context);
        preferencesManager.saveString(KEY_DERECTORY_SUBCATAGORY_DATA, json);
        preferencesManager.saveBoolean(KEY_HAS_DERECTORY_SUBCATAGORY_DATA, true);
    }

    public static String getUserLang(Context context) {
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        return sharedPreferences.getString("language");
    }

    public static void clearAllLastUpdatedDate(Context context) {
        AppLog.i(TAG, "Clearing all Last Update");
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(context, SharedPreferencesManager.CACHE_PREFERENCE);
        preferencesManager.deleteAll();
    }

    /**
     * this method is used to send usersetting to api
     *
     * @param context
     * @return
     */
    public static String getUserLanguage(Context context) {
        String lang = getUserLang(context);
        if (lang.equals(LandingActivityPresenter.KEY_CHINESE)) {
            return "zh-hans";
        }
        return lang;
    }

    public static boolean hasExchangeRateData(Context context) {
        return false;
    }
}
