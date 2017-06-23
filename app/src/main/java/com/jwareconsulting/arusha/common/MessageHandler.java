package com.jwareconsulting.arusha.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.jwareconsulting.arusha.R;

/**
 * Created by anjul khanal on 5/5/16.
 */
public class MessageHandler {

    /**
     * provides toast notification
     *
     * @param context
     * @param message
     */
    public static void toast(Activity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void alertMessage(Activity activity, String alertTitle, String message, DialogInterface.OnClickListener callback) {

      /*
//
        builder.setMessage(activity.getString(R.string.are_you_sure));
        builder.setPositiveButton(activity.getString(R.string.button_ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        )*/

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(alertTitle);
        builder.setMessage(message);
        builder.setPositiveButton(activity.getString(R.string.button_ok), callback);

        builder.setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
//  git remote add origin https://gitlab.com/anjul625.demo/demo.git
    }
}