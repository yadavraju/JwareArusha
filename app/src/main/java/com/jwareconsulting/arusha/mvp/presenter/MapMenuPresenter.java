/*
package com.view9.jwareMap.mvp.presenter;

import android.support.v7.app.AppCompatActivity;

import com.view9.jwareMap.model.DirectoryModel;
import com.view9.jwareMap.model.ExchangeRateModel;
import com.view9.jwareMap.mvp.ExchangeRateMvp;
import com.view9.jwareMap.mvp.LandingActivityMvp;
import com.view9.jwareMap.mvp.MapMenuMvp;
import com.view9.jwareMap.mvp.model.ExchangeMvpModel;
import com.view9.jwareMap.rest.response.model.ExchangeRateResponse;
import com.view9.jwareMap.ui.fragment.ExchangeRateFragment;
import com.view9.jwareMap.ui.fragment.MapMenufragment;

import java.util.ArrayList;

*/
/**
 * Created by anjul khanal on 8/4/16.
 *//*

public class MapMenuPresenter implements MapMenuMvp.Presenter, ExchangeMvpModel.Callback {
    private MapMenuMvp.Model mvpModel;
    private MapMenuMvp.View mvpView;

    public MapMenuPresenter(AppCompatActivity activity, MapMenufragment mapMenuFragment) {
        mvpModel = new ExchangeMvpModel(this);
        mvpModel.init(activity);
        mvpView = mapMenuFragment;
    }

    @Override
    public void OnDataArrived(ArrayList<ExchangeRateModel> dataList, ExchangeRateResponse.AuthorModel authorModel) {
        mvpView.populateData(dataList, authorModel);
    }

    @Override
    public void OnFailure(String msg) {
        mvpView.showMsg(msg);
    }



    @Override
    public void onClick(int position) {

    }

    @Override
    public void onAttach(LandingActivityMvp.Presenter mainPresenter) {

    }

    @Override
    public void onDetach() {

    }
}

*/
