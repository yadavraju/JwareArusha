package com.jwareconsulting.arusha.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends Activity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private int progressStatus = 0;
    private retrofit2.Callback<DirectoryResponse> apiDirectoryCallback;
    private ArrayList<ServiceProviderModel> dataList;
    private Handler handler = new Handler();
    private Callback<MapServiceProviderResponse> apiServiceProviderCallback;
    private boolean hasLoadedServiceProviderData, hasLoadedDirectoryData;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        hasLoadedServiceProviderData = PrefenceUtil.hasMapServiceProvider(getApplicationContext());
        hasLoadedDirectoryData = PrefenceUtil.hasDirectoryData(getApplicationContext());
        apiServiceProviderCallback = new Callback<MapServiceProviderResponse>() {
            @Override
            public void onResponse(Call<MapServiceProviderResponse> call, Response<MapServiceProviderResponse> response) {
                if (response.code() == 200) {
                    MapServiceProviderResponse mapServiceProviderResponse = response.body();
                    if (mapServiceProviderResponse.isSuccess()) {
                        String jsonString = new Gson().toJson(mapServiceProviderResponse, MapServiceProviderResponse.class);
                        PrefenceUtil.setMapServiceProvider(getApplicationContext(), jsonString);
                        String lastUpdate = mapServiceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            PrefenceUtil.storeLastUpdate(SplashActivity.this, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_MAP, lastUpdate);
                        }
                        setHasLoadedServiceProviderData(true);
                    } else {
                        MessageHandler.toast(SplashActivity.this, mapServiceProviderResponse.getMessage());
                        finishApplication();
                    }

                } else if (response.code() == 204) {
                    setHasLoadedServiceProviderData(true);
                    AppLog.i(TAG, "map service provider data is uptodate");
                } else if (response.code() == 500) {
                    MessageHandler.toast(SplashActivity.this, getResources().getString(R.string.server_error));
                    finishApplication();
                } else {
                    MessageHandler.toast(SplashActivity.this, getResources().getString(R.string.error) + response.code());
                    finishApplication();
                }

            }

            @Override
            public void onFailure(Call<MapServiceProviderResponse> call, Throwable t) {
                MessageHandler.toast(SplashActivity.this, t.getMessage());
                finishApplication();
            }
        };
        apiDirectoryCallback = new retrofit2.Callback<DirectoryResponse>() {
            @Override
            public void onResponse(Call<DirectoryResponse> call, Response<DirectoryResponse> response) {
                if (response.code() == 200) {
                    DirectoryResponse directoryResponse = response.body();
                    if (directoryResponse.isSuccess()) {
                        String jsonString = new Gson().toJson(directoryResponse, DirectoryResponse.class);
                        PrefenceUtil.setDirectoryData(getApplicationContext(), jsonString);
                        String lastUpdate = directoryResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            PrefenceUtil.storeLastUpdate(SplashActivity.this, PrefenceUtil.KEY_SYNC_ALL_SERVICE_PROVIDER_BY_CATAGORY, lastUpdate);
                        } else {
                            AppLog.e(TAG, "lastupdate field is null");
                        }
                        setHasLoadedDirectoryData(true);
                        delayMethod(progressBar);
                    } else {
                        MessageHandler.toast(SplashActivity.this, directoryResponse.getMessage());
                        finishApplication();
                    }
                } else if (response.code() == 204) {
                    setHasLoadedDirectoryData(true);
                    AppLog.i(TAG, "service provider data is uptodate");
                } else if (response.code() == 500) {
                    MessageHandler.toast(SplashActivity.this, getResources().getString(R.string.server_error));
                    finishApplication();
                } else {
                    MessageHandler.toast(SplashActivity.this, getResources().getString(R.string.error) + response.code());
                    finishApplication();
                }
            }

            @Override
            public void onFailure(Call<DirectoryResponse> call, Throwable t) {
                MessageHandler.toast(SplashActivity.this, t.getMessage());
                finishApplication();
            }
        };

        if (AppUtils.hasInternet(getApplicationContext())) {
            ApiManager.getServiceProviderForMap(apiServiceProviderCallback, getApplicationContext());
            ApiManager.getAllServiceProviderCatagory(apiDirectoryCallback, getApplicationContext());
            delayMethod(progressBar);
        } else {
            showNoInternetConnectionDialogue();
        }


    }

    private void finishApplication() {
        if (hasLoadedDirectoryData() && hasLoadedServiceProviderData()) {
            delayMethod(progressBar);
        } else {
            finish();
        }

    }

    private void delayMethod(final ProgressBar pb) {
        // Set the progress status zero on each button click
        progressStatus = 0;
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10;
                    handler.post(new Runnable() {
                        public void run() {
                            pb.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100) {
                    startNextActivity();
                }
            }
        }).start();
    }

    public void startNextActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public boolean hasLoadedServiceProviderData() {
        return hasLoadedServiceProviderData;
    }

    public void setHasLoadedServiceProviderData(boolean hasLoadedServiceProviderData) {
        this.hasLoadedServiceProviderData = hasLoadedServiceProviderData;
    }

    public boolean hasLoadedDirectoryData() {
        return hasLoadedDirectoryData;
    }

    public void setHasLoadedDirectoryData(boolean hasLoadedDirectoryData) {
        this.hasLoadedDirectoryData = hasLoadedDirectoryData;
    }

    // Display message in dialog box if you have not internet connection
    public void showNoInternetConnectionDialogue() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getString(R.string.no_internet_connected));
        alertDialogBuilder.setMessage(getString(R.string.no_internet_detail));
        alertDialogBuilder.setPositiveButton(getString(R.string.button_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                finishApplication();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
