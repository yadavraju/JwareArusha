package com.jwareconsulting.arusha.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jwareconsulting.arusha.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by anjul on 3/9/15.
 */
public class AppUtils {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static String TAG = AppUtils.class.getSimpleName();

    /**
     * @param context
     * @return
     */
    public static boolean hasInternet(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
        if (context == null)
            return false;

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static String DateConvert(String Date) {//2016-12-31
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("dd MMM yyyy");
        java.util.Date dDate = null;
        try {
            dDate = sdfmt1.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = sdfmt2.format(dDate);
        return strOutput;
    }

    public static String DateConvertDayOnly(String Date) {//2016-12-31
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("dd");
        java.util.Date dDate = null;
        try {
            dDate = sdfmt1.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = sdfmt2.format(dDate);
        return strOutput;
    }

    public static String DateConvertMonthYear(String Date) {//2016-12-31
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM yyyy");
        java.util.Date dDate = null;
        try {
            dDate = sdfmt1.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = sdfmt2.format(dDate);
        return strOutput;
    }

    public static String DateConvertMonthOnly(String Date) {//2016-12-31
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
        java.util.Date dDate = null;
        try {
            dDate = sdfmt1.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = sdfmt2.format(dDate);
        return strOutput;
    }

    public static String DateConvertYearOnly(String Date) {//2016-12-31
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("yyyy");
        java.util.Date dDate = null;
        try {
            dDate = sdfmt1.parse(Date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = sdfmt2.format(dDate);
        return strOutput;
    }

    /**
     * opens specific url in browser
     *
     * @param context
     * @param url
     */
    public static void openINBrowser(Activity context, String url) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(i);
            Toast.makeText(context, "Opening in browser", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Invalid Url", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * calls specific number
     *
     * @param context
     * @param contactNumber to be called
     */
    public static void makeCall (final Activity context,final String contactNumber) {


        MessageHandler.alertMessage(context,context.getResources().getString(R.string.call_title),context.getResources().getString(R.string.call_message), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(context, "calling:" + contactNumber, Toast.LENGTH_SHORT).show();
                AppLog.i(TAG, "calling:" + contactNumber);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contactNumber));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);//todo add calling permissions
            }
        });





    }

    /**
     * @param activity
     * @param emailAddress
     */
    public static void launchEmailIntent(Activity activity, String emailAddress) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{emailAddress});
        i.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.email_subject));
        i.putExtra(Intent.EXTRA_TEXT, activity.getResources().getString(R.string.email_body));
        try {
            activity.startActivity(i);
            Toast.makeText(activity, "Launching email application", Toast.LENGTH_SHORT).show();
            AppLog.i(TAG, "launching email application:" + emailAddress);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "No email Application installed", Toast.LENGTH_SHORT).show();
            AppLog.printStackStrace(ex);
        }

    }

    public static void makeWhatsAppCall(AppCompatActivity activity, String data) {

    }

    public static void makeSkypeCall(AppCompatActivity activity, String data) {
        try {
            Intent sky = new Intent("android.intent.action.VIEW");
            sky.setData(Uri.parse("skype:" + data));
            activity.startActivity(sky);
        } catch (Exception e) {
            Toast.makeText(activity, "Skype not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    /*
    *Share through facebook
    */
    public static void shareAppLinkViaFacebook(Context activity) {
        String urlToShare = "https://www.facebook.com/JWareConsultingFirm";

        try {
            Intent intent1 = new Intent();
            intent1.setPackage("com.facebook.katana");//, "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", urlToShare);
            activity.startActivity(intent1);
        } catch (Exception e) {
            // If we failed (not native FB app installed), try share through SEND
            Intent intent = new Intent(Intent.ACTION_SEND);
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            activity.startActivity(intent);
        }
    }

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static void openWhatsApp(String id, Context context) {
        try {
            Uri uri = Uri.parse("smsto:" + id);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            context.startActivity(Intent.createChooser(i, ""));
        } catch (Exception e) {
            Toast.makeText(context,context.getResources().getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT)
                    .show();
        }

    }
    public static void openViber(String id, Context context) {
        try {
            Uri uri = Uri.parse("tel:" + Uri.encode(id));
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context,context.getResources().getString(R.string.viber_not_installed), Toast.LENGTH_SHORT)
                    .show();
        }

    }


    /**
     * hide keyboard
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void hideKeyBoard(View view, Activity activity) {
        view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String formatDate(String startingDate, String endingDate) {
        AppLog.d(TAG, "##############################################################################################################################################################");
        AppLog.d(TAG, "date from :" + startingDate + "    date to " + endingDate);
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        Date fromDate = null;
        Date toDate = null;
        Date fromMonthDate = null;
        Date toMonthDate = null;
        String formattedMonth = "";
        String formattedYear = "";
        try {
            fromDate = sdfmt1.parse(startingDate);
            toDate = sdfmt1.parse(endingDate);
            fromMonthDate = month.parse(startingDate);
            toMonthDate = month.parse(endingDate);
            AppLog.e(TAG, "parsed starting date:   " + fromDate);
            AppLog.e(TAG, "parsed ending date:   " + toDate);
            String fromYear = year.format(fromDate);
            String toYear = year.format(toDate);
//            String fromMonth = month.format(fromMonthDate);
       //todo     String fromMonth = (String) android.text.format.DateFormat.format("MMM", fromMonthDate);
            String fromMonth = DateConvertMonthOnly(startingDate);


//            String testData= new SimpleDateFormat(startingDate,"MMM");

            AppLog.e(TAG, "parsed value for from month : " + fromMonth);
//            String toMonth = month.format(toMonthDate);


//            String toMonth = (String) android.text.format.DateFormat.format("MMM", toMonthDate);
            String toMonth =DateConvertMonthOnly(endingDate);
            AppLog.e(TAG, "parsed value for to month : " + toMonth);
            String fromDay = day.format(fromDate);
            String toDay = day.format(toDate);

            if (fromYear.equals(toYear)) {
                AppLog.d(TAG, "Both year equals each other" + "  " + fromYear + " " + toYear);
                formattedYear = toYear;
                if (fromMonth.equals(toMonth)) {
                    AppLog.d(TAG, "Month equals " + fromMonth + " :+ " + toMonth);
                    formattedMonth = toMonth;
                    /*if (fromDay.equals(toDay)) {
                        AppLog.d(TAG, "Day equals each other: " + fromDay + "  " + toDay);
                    } else {
                        AppLog.e(TAG, "Day  donot equals each other: " + fromDay + "  " + toDay);
                    }*/
                } else {
                    AppLog.e(TAG, "Month donot  equals " + fromMonth + " : " + toMonth);
                    formattedMonth = fromMonth + "-" + toMonth;
                }

            } else {
                AppLog.e(TAG, "year doesnot equal each other");
                formattedYear = fromYear + "-" + toYear;
                if (fromMonth.equals(toMonth)) {
                    AppLog.d(TAG, "Month equals " + fromMonth + " : " + toMonth);
                    formattedMonth = toMonth;
                    /*if (fromDay.equals(toDay)) {
                        AppLog.d(TAG, "Day equals each other: " + fromDay + "  " + toDay);
                    } else {
                        AppLog.e(TAG, "Day  donot equals each other: " + fromDay + "  " + toDay);
                    }*/
                } else {
                    AppLog.e(TAG, "Month donot  equals " + fromMonth + " : " + toMonth);
                    formattedMonth = fromMonth + "-" + toMonth;
                }

            }


        } catch (ParseException e) {
            AppLog.printStackStrace(e);
            return "";
        }
        return formattedMonth + ", " + formattedYear;
    }



    public static String getCharacterAfterSymbol(String symbol, String characterToSplit) throws Exception {
        String[] parts = characterToSplit.split(symbol);
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034
        return part2;
    }
}
