package com.jwareconsulting.arusha.common;

import android.text.TextUtils;

/**
 * Created by anjulkhanal on 8/23/16.
 */
public class ValidationUtils {

    /**
     * return true if is empty
     *
     * @param title
     * @return
     */
    public static boolean isEmpty(String title) {
        if (title == null) {
            return true;
        } else {

            if (title.equals("null") || TextUtils.isEmpty(title)) {
                return true;
            } else {
                return false;
            }


        }


    }


}
