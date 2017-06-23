package com.jwareconsulting.arusha.common;

import android.database.Cursor;
import android.util.Log;


/**
 * Created by anjul khanal on 2/22/16.
 */
public class AppLog {
    //added new file
    public static final boolean ALL_LOGS = true;
    public static final String ALL_LOG_TAG = "filtered_tag_arusha";
    public static final boolean ENABLE_LOG = true;
    private static final boolean DEBUG = true && ENABLE_LOG;
    private static final boolean VERBOSE = true && ENABLE_LOG;
    private static final boolean TEMP = true && ENABLE_LOG;
    private static final boolean WARNING = true && ENABLE_LOG;
    private static final boolean INFO = true & ENABLE_LOG;
    private static final boolean ERROR = true && ENABLE_LOG;

    /**
     * All application tags *
     */

    public static void d(String tag, String msg) {
        if (ALL_LOGS) {
            Log.d(ALL_LOG_TAG, tag + "--------" + msg);
        } else {
            if (DEBUG) {
                Log.d(tag, msg);
            }
        }


    }

    public static void d(boolean bool, String tag, String msg) {
        if (ENABLE_LOG)
            Log.d(tag, msg);

    }

    public static void i(boolean bool, String tag, String msg) {
        if (ENABLE_LOG & bool)
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (ALL_LOGS) {
            Log.i(ALL_LOG_TAG, tag + "--------" + msg);
        } else {

            if (INFO) {
                Log.i(tag, msg);
            }

        }
    }

    public static void e(String tag, String msg) {

        if (ALL_LOGS) {
            Log.e(ALL_LOG_TAG, tag + "--------" + msg);
        } else {


        }
    }

    public static void e(boolean bool, String tag, String msg) {
        if (ENABLE_LOG)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (ALL_LOGS) {
            Log.v(ALL_LOG_TAG, tag + "--------" + msg);
        } else {

            if (VERBOSE)
                Log.v(tag, msg);
        }
    }

    public static void response(String tag, String msg) {
        if (ALL_LOGS) {
            Log.v(ALL_LOG_TAG, tag + " response from server###    " + msg);
        } else {

            if (VERBOSE)
                Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (ALL_LOGS) {
            Log.w(ALL_LOG_TAG, tag + "--------" + msg);
        } else {
            if (WARNING)
                Log.w(tag, msg);
        }
    }

    public static void printStackStrace(Exception e) {
        Log.w(ALL_LOG_TAG, Log.getStackTraceString(e));
    }

    public static void w(String tag, String msg, Exception e) {
        if (ENABLE_LOG)
            Log.w(tag, msg, e);
    }

    public static void printCursor(Cursor cursor) {


        if (cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                int columnsQty = cursor.getColumnCount();
                for (int idx = 0; idx < columnsQty; ++idx) {
                    sb.append(cursor.getString(idx));
                    if (idx < columnsQty - 1)
                        sb.append("; ");
                }
                AppLog.v("database_result", String.format("Row: %d, Values: %s", cursor.getPosition(), sb.toString()));

            } while (cursor.moveToNext());
        }
    }

}