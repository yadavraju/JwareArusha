package com.jwareconsulting.arusha.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.CutomeAddBottomViewPagerAdapter;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MultiLanguageApp;
import com.jwareconsulting.arusha.common.PermissionUtils;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.presenter.LandingActivityPresenter;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.CustomAddResponseModel;
import com.jwareconsulting.arusha.ui.fragment.MainFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LandingActivityMvp.View {
    public static final int DELAY_TIME = 0;
    public static final int PERIOD_TIME = 3000;
    private static ImageView ivFotterAdd;
    public ViewPager pager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Runnable update;
    boolean isConnected;
    NetworkChangeReceiver receiver;
    private ActionBarDrawerToggle abToggle;
    private LandingActivityMvp.Presenter mPresenter;
    private ImageView ivBack, ivCustomeAdd;
    private String TAG = MainActivity.class.getSimpleName();
    private String lang;
    private Handler handler;
    private int currentPage;
    private AdView mAdView;
    private CutomeAddBottomViewPagerAdapter adsAdapter;
    private CustomAddResponseModel serviceProviderResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(this);
        lang = sharedPreferences.getString("language");*/
        lang = PrefenceUtil.getUserLang(this);
        if (lang != null) {
            MultiLanguageApp.setLocale(this, lang);
        }
        initView();
        //Addsence
        if (AppUtils.hasInternet(MainActivity.this)) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            mAdView.loadAd(adRequest);
            ivFotterAdd.setVisibility(View.GONE);
        } else {
            ivFotterAdd.setVisibility(View.VISIBLE);
        }
        mPresenter.loadFragment(toolbar);
        PermissionUtils.checkLocationPermission(MainActivity.this);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int totalFragment = getSupportFragmentManager().getBackStackEntryCount();
                if (totalFragment != 0) {
                    FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(totalFragment - 1);
                    mPresenter.onBackStackChanged(backEntry.getName().toString(), ivBack);
                } else if (totalFragment == 0) {
                    mPresenter.onBackStackChanged(MainFragment.TAG, ivBack);
                }

            }
        });
        if (AppUtils.hasInternet(MainActivity.this)) {
            CustomeAddTask();
            ivCustomeAdd.setVisibility(View.GONE);
        } else {
            ivCustomeAdd.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivBack = (ImageView) findViewById(R.id.iv_back_arrow);
        setSupportActionBar(toolbar);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.hideKeyBoard(v, MainActivity.this);
                onBackPressed();
            }
        });

        //Addvertisement layout
        mAdView = (AdView) findViewById(R.id.adView);
        pager = (ViewPager) findViewById(R.id.viewpager);
        ivCustomeAdd = (ImageView) findViewById(R.id.iv_offline_add_image);
        ivFotterAdd = (ImageView) findViewById(R.id.iv_offline_add_image_footer);

        mPresenter = new LandingActivityPresenter(MainActivity.this);
        mPresenter.loadFragment(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        abToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        abToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();
        mPresenter.setNavigationView((NavigationView) findViewById(R.id.nav_view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sort) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public LandingActivityMvp.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void changeAds(String title) {
        if (adsAdapter != null) {
            //CustomeAddTask();
            //todo comment now         setAdapterData(serviceProviderResponse.getDataList());
            adsAdapter.onPageChanged(title);
            //adsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    private void CustomeAddTask() {

        Callback<CustomAddResponseModel> callback = new Callback<CustomAddResponseModel>() {
            @Override
            public void onResponse(Call<CustomAddResponseModel> call, Response<CustomAddResponseModel> response) {
                AppLog.e(TAG, response.toString());
                if (response.code() == 200) {
                    serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        setAdapterData(serviceProviderResponse.getDataList());
                    } else {
                        Toast.makeText(MainActivity.this, serviceProviderResponse.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 500) {
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomAddResponseModel> call, Throwable t) {
            }
        };

        ApiManager.getCostumAdd(getApplicationContext(), callback);
    }

    private void setAdapterData(final ArrayList<CustomAddResponseModel.CustomeAddData> dataList) {
        handler = new Handler();
        adsAdapter = new CutomeAddBottomViewPagerAdapter(MainActivity.this, dataList);
        pager.setAdapter(adsAdapter);
        adsAdapter.setOnClickListner(new CutomeAddBottomViewPagerAdapter.OnClickListner() {
            @Override
            public void onClick(String url) {
                AppUtils.openINBrowser(MainActivity.this, url);
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if (position == dataList.size()) {
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        update = new Runnable() {
            public void run() {
                if (currentPage == dataList.size()) {
                    currentPage = 0;
                }
                pager.setCurrentItem(currentPage++, true);
            }
        };


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.checkPermission(requestCode, permissions, grantResults);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }

    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {

            Log.e("broadcast", "Received notification about network status");
            isNetworkAvailable(context);
        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            if (!isConnected) {
                                Log.e("broadcast", "Now you are connected to Internet!");
                                mAdView.setVisibility(View.VISIBLE);
                                AdRequest adRequest = new AdRequest.Builder()
                                        .build();
                                mAdView.loadAd(adRequest);
                                ivFotterAdd.setVisibility(View.GONE);
                                CustomeAddTask();
                                ivCustomeAdd.setVisibility(View.GONE);
                                isConnected = true;
                            }
                            return true;
                        }
                    }
                }
            }
            Log.e("broadcast", "You are not connected to Internet!");
            // Toast.makeText(getActivity(), "You are not connected to Internet!", Toast.LENGTH_SHORT).show();
            mAdView.setVisibility(View.GONE);
            ivFotterAdd.setVisibility(View.VISIBLE);
            ivCustomeAdd.setVisibility(View.VISIBLE);
            isConnected = false;
            return false;
        }
    }
}
