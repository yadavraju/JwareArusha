package com.jwareconsulting.arusha.common;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by anjulkhanal  on 7/21/16.
 */
public class PermissionUtils {
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 998;
    private static final int REQUEST_CODE_PERMISSION_GPS = 997;
    private static final int REQUEST_CODE_PERMISSION_RW_FILE = 996;
    private static final int REQUEST_CODE_PERMISSION_READ_PHONE_STATE = 995;

    private static final String TAG = PermissionUtils.class.getSimpleName();

    public static void checkLocationPermission(Activity context) {

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION_GPS);
        }

    }

    public static void checkWriteExternalStoragePermission(Activity context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_RW_FILE);
        }

    }

    public static void checkReadPhoneStatePermission(Activity context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_PERMISSION_READ_PHONE_STATE);
        }
    }


    public static void checkCameraPermission(Activity context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
        }

    }


    public static void checkPermission(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case REQUEST_CODE_PERMISSION_CAMERA:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppLog.i(TAG, "permission has been granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    AppLog.w(TAG, "permission not provided by user to open camera");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            case REQUEST_CODE_PERMISSION_GPS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppLog.i(TAG, "permission has been granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    AppLog.w(TAG, "permission not provided by user to open camera");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

                break;


        }

    }
}
