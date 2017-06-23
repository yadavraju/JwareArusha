package com.jwareconsulting.arusha.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MultiLanguageApp;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.common.SharedPreferencesManager;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.MainFragmentMvp;
import com.jwareconsulting.arusha.mvp.presenter.MainFragmentPresenter;
import com.jwareconsulting.arusha.ui.activity.MainActivity;
import com.jwareconsulting.arusha.ui.view.HorizontalTileView;
import com.jwareconsulting.arusha.ui.view.RadioGroupPlus;

import java.util.Random;

/**
 * Created by root on 8/3/16.
 */
public class MainFragment extends Fragment implements MainFragmentMvp.View {
    public static final String TAG = MainFragment.class.getSimpleName();
    private static SharedPreferencesManager sharedPreferences;
    final int[] setImg = {R.drawable.mt_meru, R.drawable.tower, R.drawable.zebra};
    RelativeLayout shareView, rlChangeLang, rlChooseLang, htvTopTenThings1;
    RadioButton rbu1, rbu2, rbu3, rbu4, rbu5, rbu6, rbu7, rbu8;
    Dialog dialog;
    private View view, htvTopTenThings;
    private HorizontalTileView htvMap, htvDirectory, htvEvents, htvExchangeRate;
    private MainFragmentMvp.Presenter mPresenter;
    private LandingActivityMvp.Presenter mainPresenter;
    private ImageView ivChangeLangBack;
    private RadioGroupPlus mRadioGroupPlus;
    private ImageView ivChangeLanguage;
    private ImageView ivBackground;
    private TextView tvChangeLanguage;
    private Typeface font;

    private static final String Locale_KeyValue = "Saved Locale";
    public static final String KEY_ENG = "en";
    public static final String KEY_SWAIL = "sw";
    public static final String KEY_FRENCH = "fr";
    public static final String KEY_NL = "nl";
    public static final String KEY_SPANISH = "es";
    public static final String KEY_CHINESE = "zh";
    public static final String KEY_DE = "de";
    public static final String KEY_ITALIAN = "it";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        sharedPreferences = new SharedPreferencesManager(getActivity());
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts" + "/" + getString(R.string.proxima_font_regular));
        init(view);
        setRandomBackbroundImage();
        setLisner();
        return view;

    }

    private void setRandomBackbroundImage() {
        int[] mbgIds = new int[]{R.drawable.zebra, R.drawable.tower, R.drawable.mt_meru};
        Random rnd = new Random();
        Integer u = mbgIds[rnd.nextInt(mbgIds.length)];
        Log.e(TAG, "IMAGE_GET" + u);
        ivBackground.setBackgroundResource(u);
    }

    private void init(View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.GONE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);

        htvMap = (HorizontalTileView) view.findViewById(R.id.hv_map);
        htvDirectory = (HorizontalTileView) view.findViewById(R.id.hv_directory);
        htvEvents = (HorizontalTileView) view.findViewById(R.id.hv_events);
        htvExchangeRate = (HorizontalTileView) view.findViewById(R.id.hv_exchange_rate);
        htvTopTenThings = (View) view.findViewById(R.id.hv_top_tenthings);
        shareView = (RelativeLayout) view.findViewById(R.id.tile_share);
        rlChangeLang = (RelativeLayout) view.findViewById(R.id.tile_language);
        rlChooseLang = (RelativeLayout) view.findViewById(R.id.rl_selectlanguage);
        ivChangeLangBack = (ImageView) view.findViewById(R.id.iv_change_language_back);
        //ivChangeLanguage = (ImageView) view.findViewById(R.id.iv_change_language);
        tvChangeLanguage = (TextView) view.findViewById(R.id.iv_change_language);
        ivBackground = (ImageView) view.findViewById(R.id.iv_background);

/*        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(ContextCompat.getDrawable(getActivity(),R.drawable.mt_meru), 10000);
        animation.addFrame(ContextCompat.getDrawable(getActivity(),R.drawable.tower), 10000);
        animation.addFrame(ContextCompat.getDrawable(getActivity(),R.drawable.zebra), 10000);
        animation.setOneShot(false);
        ivBackground.setImageDrawable(animation);

        // start the animation!
        animation.start();*/
        rlChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareView.setVisibility(View.GONE);
                rlChangeLang.setVisibility(View.GONE);
                rlChooseLang.setVisibility(View.VISIBLE);
            }
        });
        ivChangeLangBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareView.setVisibility(View.VISIBLE);
                rlChangeLang.setVisibility(View.VISIBLE);
                rlChooseLang.setVisibility(View.GONE);
            }
        });

        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.shareAppLinkViaFacebook(getActivity());
            }
        });
        mRadioGroupPlus = (RadioGroupPlus) view.findViewById(R.id.radio_group_plus);
        rbu1 = (RadioButton) view.findViewById(R.id.rb_eng);
        rbu2 = (RadioButton) view.findViewById(R.id.rb_swa);
        rbu3 = (RadioButton) view.findViewById(R.id.rb_fra);
        rbu4 = (RadioButton) view.findViewById(R.id.rb_dut);
        rbu5 = (RadioButton) view.findViewById(R.id.rb_spa);
        rbu6 = (RadioButton) view.findViewById(R.id.rb_chn);
        rbu7 = (RadioButton) view.findViewById(R.id.rb_deu);
        rbu8 = (RadioButton) view.findViewById(R.id.rb_ita);

        rbu1.setTypeface(font);
        rbu2.setTypeface(font);
        rbu3.setTypeface(font);
        rbu4.setTypeface(font);
        rbu5.setTypeface(font);
        rbu6.setTypeface(font);
        rbu7.setTypeface(font);
        rbu8.setTypeface(font);

    }

    private void setLisner() {
        htvMap.setmListner(new HorizontalTileView.ClickListner() {
            @Override
            public void OnClicked() {
                mPresenter.OnClick(R.id.hv_map);
            }
        });
        htvDirectory.setmListner(new HorizontalTileView.ClickListner() {
            @Override
            public void OnClicked() {
                mPresenter.OnClick(R.id.hv_directory);
            }
        });
        htvEvents.setmListner(new HorizontalTileView.ClickListner() {
            @Override
            public void OnClicked() {
                mPresenter.OnClick(R.id.hv_events);
            }
        });
        htvExchangeRate.setmListner(new HorizontalTileView.ClickListner() {
            @Override
            public void OnClicked() {
                mPresenter.OnClick(R.id.hv_exchange_rate);

            }
        });
        htvTopTenThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.OnClick(R.id.hv_top_tenthings);
            }
        });


//        sharedPreferences.getString("language");
        checkDefaulRadioButtion(sharedPreferences.getString("language"));



        mRadioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
//     todo           onOrderClicked(radioGroupPlus);
//
           /*     if (mainPresenter != null) {
                    mainPresenter.onOrderClicked(radioGroupPlus);
                } */

                onOrderClicked(radioGroupPlus,getActivity());

            }
        });

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
        mPresenter = new MainFragmentPresenter(MainFragment.this);
        mPresenter.onAttach(mainPresenter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

    @Override
    public void checkDefaulRadioButtion(String languageCode) {
        AppLog.i(TAG,"default language is : "+languageCode);
        if (languageCode.equalsIgnoreCase("en")) {
            rbu1.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_eng_act);
            tvChangeLanguage.setText("ENG");
        } else if (languageCode.equalsIgnoreCase("sw")) {
            rbu2.setChecked(true);
            Log.e("Language", rbu2 + "");
            //ivChangeLanguage.setImageResource(R.drawable.lan_swa_act);
            tvChangeLanguage.setText("SWA");
        } else if (languageCode.equalsIgnoreCase("fr")) {
            rbu3.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_fra_act);
            tvChangeLanguage.setText("FRA");
        } else if (languageCode.equalsIgnoreCase("nl")) {
            rbu4.setChecked(true);
            Log.e("Language", rbu4 + "");
            //ivChangeLanguage.setImageResource(R.drawable.lan_dut_act);
            tvChangeLanguage.setText("NL");
        } else if (languageCode.equalsIgnoreCase("es")) {
            rbu5.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_spa_act);
            tvChangeLanguage.setText("SPA");
        } else if (languageCode.equalsIgnoreCase("zh")) {
            rbu6.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_ch_act);
            tvChangeLanguage.setText("中文");
        } else if (languageCode.equalsIgnoreCase("de")) {
            rbu7.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_deu_act);
            tvChangeLanguage.setText("DEU");
        } else if (languageCode.equalsIgnoreCase("it")) {
            rbu8.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_ita_act);
            tvChangeLanguage.setText("ITA");
        } else {
            rbu1.setChecked(true);
            //ivChangeLanguage.setImageResource(R.drawable.lan_eng_act);
            tvChangeLanguage.setText("ENG");
        }
    }



    @Override
    public void onResume() {
        super.onResume();
//        MainActivity.offlineMode(getActivity());
    }


    public void onOrderClicked(RadioGroupPlus view,Activity activity) {
        if (R.id.rb_eng == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ENG,activity);
        } else if (R.id.rb_swa == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SWAIL,activity);
        } else if (R.id.rb_fra == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_FRENCH,activity);
        } else if (R.id.rb_dut == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_NL,activity);
        } else if (R.id.rb_spa == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_SPANISH,activity);
        } else if (R.id.rb_chn == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_CHINESE,activity);
        } else if (R.id.rb_deu == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_DE,activity);
        } else if (R.id.rb_ita == view.getCheckedRadioButtonId()) {
            LanguageConfirmation(KEY_ITALIAN,activity);
        } else {

        }
    }


    public void LanguageConfirmation(final String Lan, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
//        builder.setTitle(activity.getString(R.string.logout_con_tittle));
        builder.setMessage(activity.getString(R.string.are_you_sure));
        builder.setPositiveButton(activity.getString(R.string.button_ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferences.saveString("language", Lan);
                MultiLanguageApp.setLocale(activity, Lan);
                PrefenceUtil.clearAllLastUpdatedDate(activity);
                restartApplication(activity);
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

    void restartApplication(Activity activity) {
        Intent i = new Intent(activity, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

}

