package com.jwareconsulting.arusha.mvp.presenter;

import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.model.ExchangeRateModel;
import com.jwareconsulting.arusha.mvp.ExchangeRateMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.model.ExchangeMvpModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;
import com.jwareconsulting.arusha.ui.fragment.ExchangeRateFragment;
import com.jwareconsulting.arusha.ui.fragment.MapMenufragment;

import java.util.ArrayList;

/**
 * Created by anjul khanal on 8/4/16.
 */
public class ExchangeRatePresenter implements ExchangeRateMvp.Presenter, ExchangeMvpModel.Callback {
    private final AppCompatActivity activity;
    private ExchangeRateMvp.Model mvpModel;
    private ExchangeRateMvp.View mvpView;

    public ExchangeRatePresenter(AppCompatActivity activity, ExchangeRateFragment exchangeRateFragment) {
        this.activity = activity;
        mvpView = exchangeRateFragment;
        mvpModel = new ExchangeMvpModel(this);
        mvpModel.init(activity);

    }

    @Override
    public void OnDataArrived(ArrayList<ExchangeRateModel> dataList, ExchangeRateResponse.AuthorModel authorModel, String date) {
        if (mvpView != null) {
            if(dataList!=null ){
                mvpView.populateData(dataList, authorModel, date);
            }else{
                mvpView.showMsg("Error encountered");
            }

        } else {
            AppLog.e("ExchangeRate", "null");
        }
    }

    @Override
    public void OnFailure(String msg) {
        mvpView.showMsg(msg);

    }




}

