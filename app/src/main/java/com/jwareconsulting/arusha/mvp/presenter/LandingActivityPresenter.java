package com.jwareconsulting.arusha.mvp.presenter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppText;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MultiLanguageApp;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.common.SharedPreferencesManager;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.ui.activity.MainActivity;
import com.jwareconsulting.arusha.ui.fragment.DIrectorySubCatagoryFragment;
import com.jwareconsulting.arusha.ui.fragment.DirectoryDetailFragment;
import com.jwareconsulting.arusha.ui.fragment.DirectoryFragment;
import com.jwareconsulting.arusha.ui.fragment.EventDetailFragment;
import com.jwareconsulting.arusha.ui.fragment.EventsFragment;
import com.jwareconsulting.arusha.ui.fragment.ExchangeRateFragment;
import com.jwareconsulting.arusha.ui.fragment.MainFragment;
import com.jwareconsulting.arusha.ui.fragment.MapMenufragment;
import com.jwareconsulting.arusha.ui.fragment.ThingsToDoDetailFragment;
import com.jwareconsulting.arusha.ui.fragment.TopThingsTodoFragment;
import com.jwareconsulting.arusha.ui.view.RadioGroupPlus;

import java.util.Locale;

/**
 * Created by anjul khanal on 8/4/16.
 */
public class LandingActivityPresenter implements LandingActivityMvp.Presenter, View.OnClickListener {

    private static final String Locale_KeyValue = "Saved Locale";
    public static final String KEY_ENG = "en";
    public static final String KEY_SWAIL = "sw";
    public static final String KEY_FRENCH = "fr";
    public static final String KEY_NL = "nl";
    public static final String KEY_SPANISH = "es";
    public static final String KEY_CHINESE = "zh";
    public static final String KEY_DE = "de";
    public static final String KEY_ITALIAN = "it";
    private static Locale myLocale;
    private static SharedPreferencesManager sharedPreferences;
    private final MainActivity activity;
    RadioGroupPlus mRadioGroupPlus;
    Dialog dialog;
    RadioButton rbu1, rbu2, rbu3, rbu4, rbu5, rbu6, rbu7, rbu8;
    private TextView tvHome, tvMap, tvDirectory, tvEvents, tvExchangeRate, tvTopTenThings, tvShareApp, tvMail;
    private MainFragment mainFragment;
    private DirectoryFragment directoryFragment;
    private EventsFragment eventsFragment;
    private ExchangeRateFragment exchangeRateFragment;
    private TopThingsTodoFragment topThingsTodoFragment;
    private DIrectorySubCatagoryFragment DIrectorySubCatagoryFragment;
    private DirectoryDetailFragment directoryDetailFragment;
    private MapMenufragment mapMenuFragment;
    private Toolbar toolbar;
    private int contentFrag;
    private FragmentManager fragmentManager;

    //Shared Preferences Variables
    private LandingActivityMvp.View landingActivityView;
    private TextView toolbarTitle;
    private Typeface font;

    public LandingActivityPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
        landingActivityView = mainActivity;
    }

    @Override
    public void OnItemClick(int id) {
//pushed to devlopemnt branch
        switch (id) {
            case R.id.tv_home:
                replaceFragment(mainFragment, mainFragment.TAG, activity.getString(R.string.activity_title_landing));
                break;
            case R.id.tv_map:
            case R.id.hv_map:
                replaceFragment(new MapMenufragment(), mapMenuFragment.TAG, activity.getString(R.string.drawer_map), true);
                break;
            case R.id.tv_directory:
            case R.id.hv_directory:
                replaceFragment(directoryFragment, directoryFragment.TAG, activity.getResources().getString(R.string.activity_directory));
//                replaceFragment(DIrectorySubCatagoryFragment, "abc ",activity.getResources().getString(R.string.activity_directory));
                break;
            case R.id.tv_events:
            case R.id.hv_events:
                replaceFragment(eventsFragment, eventsFragment.TAG, activity.getString(R.string.text_events));
                break;
            case R.id.tv_exchange_rate:
            case R.id.hv_exchange_rate:
                replaceFragment(exchangeRateFragment, exchangeRateFragment.TAG, activity.getResources().getString(R.string.activity_exchange_rate));
                break;
            case R.id.tv_top_ten_things:
            case R.id.hv_top_tenthings:
                replaceFragment(topThingsTodoFragment, topThingsTodoFragment.TAG, activity.getString(R.string.drawer_top_10_things));
                //comitted by raju
//                replaceFragment(new DirectoryDetailFragment(), DirectoryDetailFragment.TAG,"testing here");
                break;
            case R.id.tv_share_app:
                AppUtils.shareAppLinkViaFacebook(activity);
            case R.id.tv_email_us:
                AppUtils.launchEmailIntent(activity,activity.getResources().getString(R.string.mail_address));
                break;
        }

    }

    @Override
    public void setNavigationView(NavigationView navigationView) {
        initView(navigationView);
//        loadLocale();
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag, String subtitle) {

        setTitle(subtitle);
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frag, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
            landingActivityView.closeDrawer();
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag, String subtitle, boolean value) {

        if (value) {
            setTitle(subtitle);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frag, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
            landingActivityView.closeDrawer();

        } else {
            replaceFragment(fragment, tag, subtitle);
        }
    }

    @Override
    public void loadFragment(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_text);
        fragmentManager = activity.getSupportFragmentManager();
        initDefaultFragment();
        mapMenuFragment = new MapMenufragment();
        directoryFragment = new DirectoryFragment();
        eventsFragment = new EventsFragment();
        exchangeRateFragment = new ExchangeRateFragment();
        topThingsTodoFragment = new TopThingsTodoFragment();
        DIrectorySubCatagoryFragment = new DIrectorySubCatagoryFragment();
    }

    @Override
    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void onBackStackChanged(String name, ImageView ivBackArrow) {

        if (name.equals(mainFragment.TAG)) {
            ivBackArrow.setVisibility(View.GONE);
            setTitle(activity.getString(R.string.activity_title_landing));
            landingActivityView.changeAds(AppText.KEY_ADS_HOME);
        } else if (name.equals(MapMenufragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(activity.getString(R.string.drawer_map));
            landingActivityView.changeAds(AppText.KEY_ADS_HOME);
        } else if (name.equals(eventsFragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(activity.getString(R.string.text_events));
            landingActivityView.changeAds(AppText.KEY_ADS_EVENT);
        } else if (name.equals(exchangeRateFragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(activity.getResources().getString(R.string.activity_exchange_rate));
            landingActivityView.changeAds(AppText.KEY_EXCHANGE_RATE);
        } else if (name.equals(topThingsTodoFragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(activity.getString(R.string.drawer_top_10_things));
            landingActivityView.changeAds(AppText.KEY_THINGS_TO_DO_LIST);
        } else if (name.equals(DirectoryFragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(activity.getResources().getString(R.string.activity_directory));
            landingActivityView.changeAds(AppText.KEY_ADS_DIRECTORY_CATEGORY_LIST);
        } else if (name.equals(DIrectorySubCatagoryFragment.TAG)) {
            ivBackArrow.setVisibility(View.VISIBLE);
            setTitle(DIrectorySubCatagoryFragment.getTitle());
            landingActivityView.changeAds(AppText.KEY_ADS_DIRECTORY_SUB_CATEGORY_LIST);
        } else if (name.equalsIgnoreCase(EventDetailFragment.TAG)) {
            landingActivityView.changeAds(AppText.EVENT_DETAIL);
        } else if (name.equalsIgnoreCase(DirectoryDetailFragment.TAG)) {
            landingActivityView.changeAds(AppText.DIRECTORY_DETAIL);
        } else if (name.equalsIgnoreCase(ThingsToDoDetailFragment.TAG)) {
            landingActivityView.changeAds(AppText.KEY_THINGS_TO_DO_DETAIL_LIST);
        } else if (name.equalsIgnoreCase(mapMenuFragment.TAG)) {
            landingActivityView.changeAds(AppText.KEY_MAP);
        } /*else if (name.equalsIgnoreCase(mapMenuFragment.TAG)) {
            landingActivityView.changeAds(AppText.KEY_MAP);
        }*/


    }

    @Override
    public void onOrderClicked(RadioGroupPlus view) {
        if (R.id.rb_eng == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ENG);
        } else if (R.id.rb_swa == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SWAIL);
        } else if (R.id.rb_fra == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_FRENCH);
        } else if (R.id.rb_dut == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_NL);
        } else if (R.id.rb_spa == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SPANISH);
        } else if (R.id.rb_chn == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_CHINESE);
        } else if (R.id.rb_deu == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_DE);
        } else if (R.id.rb_ita == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ITALIAN);
        } else {

        }
    }


    private void initView(NavigationView navigationView) {
        sharedPreferences = new SharedPreferencesManager(activity);

        font = Typeface.createFromAsset(activity.getAssets(), "fonts" + "/" + activity.getString(R.string.proxima_font_regular));
        tvHome = (TextView) navigationView.findViewById(R.id.tv_home);
        tvMap = (TextView) navigationView.findViewById(R.id.tv_map);
        tvDirectory = (TextView) navigationView.findViewById(R.id.tv_directory);
        tvEvents = (TextView) navigationView.findViewById(R.id.tv_events);
        tvExchangeRate = (TextView) navigationView.findViewById(R.id.tv_exchange_rate);
        tvTopTenThings = (TextView) navigationView.findViewById(R.id.tv_top_ten_things);
        //  android:id="@+id/tv_email_us"
        tvShareApp = (TextView) navigationView.findViewById(R.id.tv_share_app);
        tvMail = (TextView) navigationView.findViewById(R.id.tv_email_us);
        mRadioGroupPlus = (RadioGroupPlus) navigationView.findViewById(R.id.radio_group_plus);
        rbu1 = (RadioButton) navigationView.findViewById(R.id.rb_eng);
        rbu2 = (RadioButton) navigationView.findViewById(R.id.rb_swa);
        rbu3 = (RadioButton) navigationView.findViewById(R.id.rb_fra);
        rbu4 = (RadioButton) navigationView.findViewById(R.id.rb_dut);
        rbu5 = (RadioButton) navigationView.findViewById(R.id.rb_spa);
        rbu6 = (RadioButton) navigationView.findViewById(R.id.rb_chn);
        rbu7 = (RadioButton) navigationView.findViewById(R.id.rb_deu);
        rbu8 = (RadioButton) navigationView.findViewById(R.id.rb_ita);

        rbu1.setTypeface(font);
        rbu2.setTypeface(font);
        rbu3.setTypeface(font);
        rbu4.setTypeface(font);
        rbu5.setTypeface(font);
        rbu6.setTypeface(font);
        rbu7.setTypeface(font);
        rbu8.setTypeface(font);


        tvHome.setOnClickListener(this);
        tvMap.setOnClickListener(this);
        tvDirectory.setOnClickListener(this);
        tvEvents.setOnClickListener(this);
        tvMail.setOnClickListener(this);
        tvExchangeRate.setOnClickListener(this);
        tvTopTenThings.setOnClickListener(this);
        tvShareApp.setOnClickListener(this);

        String M = sharedPreferences.getString("language");
        if (M.equalsIgnoreCase(KEY_ENG)) {
            rbu1.setChecked(true);
        } else if (M.equalsIgnoreCase(KEY_SWAIL)) {
            rbu2.setChecked(true);
            Log.e("Language", rbu2 + "");
        } else if (M.equalsIgnoreCase(KEY_FRENCH)) {
            rbu3.setChecked(true);
        } else if (M.equalsIgnoreCase(KEY_NL)) {
            rbu4.setChecked(true);
            Log.e("Language", rbu4 + "");
        } else if (M.equalsIgnoreCase(KEY_SPANISH)) {
            rbu5.setChecked(true);
        } else if (M.equalsIgnoreCase(KEY_CHINESE)) {
            rbu6.setChecked(true);
        } else if (M.equalsIgnoreCase(KEY_DE)) {
            rbu7.setChecked(true);
        } else if (M.equalsIgnoreCase(KEY_ITALIAN)) {
            rbu8.setChecked(true);
        } else {
            rbu1.setChecked(true);
            AppLog.i("Language", rbu1 + "");
        }


        mRadioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
                // Add your logic here
                onOrderClicked(radioGroupPlus);
            }
        });

    }


    private void initDefaultFragment() {
        mainFragment = new MainFragment();
        toolbarTitle.setText(activity.getString(R.string.activity_title_landing));
        fragmentManager.beginTransaction()
                .add(R.id.content_frag, mainFragment, mainFragment.TAG)
//                .addToBackStack(mainFragment.TAG)
                .commit();
    }

    @Override
    public void onClick(View v) {
        OnItemClick(v.getId());
    }

   /* public void onOrderClicked(View view) {

        if (R.id.rb_eng == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ENG);
        } else if (R.id.rb_swa == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SWAIL);
        } else if (R.id.rb_fra == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_FRENCH);
        } else if (R.id.rb_dut == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_NL);
        } else if (R.id.rb_spa == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SPANISH);
        } else if (R.id.rb_chn == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_CHINESE);
        } else if (R.id.rb_deu == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_DE);
        } else if (R.id.rb_ita == mRadioGroupPlus.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ITALIAN);
        } else {
        }
    }*/

    void restartApplication() {
        Intent i = new Intent(activity, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    public void LanguageConfirmation(final String Lan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
//        builder.setTitle(activity.getString(R.string.logout_con_tittle));
        builder.setMessage(activity.getString(R.string.are_you_sure));
        builder.setPositiveButton(activity.getString(R.string.button_ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferences.saveString("language", Lan);
                MultiLanguageApp.setLocale(activity, Lan);
                PrefenceUtil.clearAllLastUpdatedDate(activity);
                restartApplication();
            }
        });

        builder.setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String M = sharedPreferences.getString("language");
                if (M != null) {
                    if (M.equalsIgnoreCase(KEY_ENG)) {
                        rbu1.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_SWAIL)) {
                        rbu2.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_FRENCH)) {
                        rbu3.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_NL)) {
                        rbu4.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_SPANISH)) {
                        rbu5.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_CHINESE)) {
                        rbu6.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_DE)) {
                        rbu7.setChecked(true);
                    } else if (M.equalsIgnoreCase(KEY_ITALIAN)) {
                        rbu8.setChecked(true);
                    }
                } else {
                    rbu1.setChecked(true);
                }
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
}
