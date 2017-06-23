package com.jwareconsulting.arusha.mvp;

import android.content.Context;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;

/**
 * Created by anjul khanal on 8/8/16.
 */
public abstract class MvpModel {
    public boolean NO_CONTENT=false;


    public void fetchData() {

    }


    public abstract void fetchDataFromAPi(Context context) ;
    public abstract void fetchFromCache(Context context);

    public boolean checkInternetConnection(Context context) {
        return AppUtils.hasInternet(context);
    }


    public void loadData(Context context) {

        if (checkInternetConnection(context)) {
            fetchDataFromAPi(context);
        }

        else {
            fetchFromCache(context);
            noInternet(context);
        }

    }




    private void noInternet(Context context) {
        AppLog.e("no internet",context.getString(R.string.no_internet));
        //Toast.makeText(context,context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
    }

}
