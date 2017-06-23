package com.jwareconsulting.arusha.mvp.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppText;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.mvp.DirectoryDetailMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.model.DirectoryDetailMvpModel;
import com.jwareconsulting.arusha.ui.fragment.MainFragment;
import com.jwareconsulting.arusha.ui.fragment.MapMenufragment;

import java.util.ArrayList;

/**
 * Created by anjul khanal on 8/4/16.
 */
public class DirectoryDetailPresenter implements DirectoryDetailMvp.Presenter, DirectoryDetailMvpModel.Callback {
    public static String TAG = DirectoryDetailPresenter.class.getSimpleName();
    private AppCompatActivity activity;
    private DirectoryDetailMvp.Model mvpModel;
    private DirectoryDetailMvp.View mvpView;
    private LandingActivityMvp.Presenter mainPresenter;

    public DirectoryDetailPresenter(AppCompatActivity activity, Fragment fragment, String catagoryId, int position) {
        this.activity = activity;
        mvpView = (DirectoryDetailMvp.View) fragment;
        mvpModel = new DirectoryDetailMvpModel(this);
        mvpModel.init(activity, catagoryId, position);
    }


    @Override
    public void OnDataArrived(ArrayList<ServiceProviderModel> dataList) {
        mvpView.populateData(dataList);
    }

    @Override
    public void OnFailure(String msg) {
        mvpView.showMsg(msg);
    }

    @Override
    public void setSearchKeyWord(String s) {
        mvpModel.filterData(s);
    }

    @Override
    public void onClick(int type, String data) {

        switch (type) {

            case AppText.INTENT_BROWSER:
                AppUtils.openINBrowser(activity, data);
                break;
            case AppText.INTENT_MAIL:
                AppUtils.launchEmailIntent(activity, data);
                break;
            case AppText.MAKE_CALL:
                AppUtils.makeCall(activity, data);
                break;
            case AppText.SKYPE_CALL:
                AppUtils.makeSkypeCall(activity, data);
                break;
            case AppText.WHATSAPP_CALL:
                AppUtils.openWhatsApp(data, activity);
                break;
            case AppText.VIBER_CALL:
                AppUtils.openViber(data, activity);
                break;

        }

    }

    @Override
    public void onAttach(LandingActivityMvp.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void onDetach() {
        mainPresenter = null;
    }

    @Override
    public void openInMap(String latitude, String longitude, String title) {
        mainPresenter.replaceFragment(MapMenufragment.plotLocation(latitude, longitude, title),
                MapMenufragment.TAG, activity.getString(R.string.text_map));
    }
}

