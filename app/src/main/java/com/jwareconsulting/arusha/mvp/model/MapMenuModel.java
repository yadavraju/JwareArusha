/*
package com.view9.jwareMap.mvp.model;

import android.support.v7.app.AppCompatActivity;

import com.view9.jwareMap.model.ExchangeRateModel;
import com.view9.jwareMap.mvp.ExchangeRateMvp;
import com.view9.jwareMap.mvp.MapMenuMvp;
import com.view9.jwareMap.mvp.MvpModel;
import com.view9.jwareMap.mvp.presenter.ExchangeRatePresenter;
import com.view9.jwareMap.rest.ApiManager;
import com.view9.jwareMap.rest.response.model.ExchangeRateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

*/
/**
 * Created by root on 8/4/16.
 *//*

public class MapMenuModel extends MvpModel implements MapMenuMvp.Model {
    private Callback mCallBack;
    private retrofit2.Callback<ExchangeRateResponse> apiCallback;

    public MapMenuModel() {
    }

    public MapMenuModel(final ExchangeRatePresenter exchangeRatePresenter) {
        mCallBack = exchangeRatePresenter;
        apiCallback = new retrofit2.Callback<ExchangeRateResponse>() {
            @Override
            public void onResponse(Call<ExchangeRateResponse> call, Response<ExchangeRateResponse> response) {
                if (response.code() == 200) {
                    ExchangeRateResponse exchangeRateResponse = response.body();
                    if (exchangeRateResponse.isSuccess()) {
                        mCallBack.OnDataArrived(exchangeRateResponse.getDataList(), exchangeRateResponse.getAuthorModel());
                    } else {
                        mCallBack.OnFailure(exchangeRateResponse.getMessage());
                    }
                } else if (response.code() == 500) {
                    mCallBack.OnFailure("Server Error:");
                } else {
                    mCallBack.OnFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ExchangeRateResponse> call, Throwable t) {
                mCallBack.OnFailure(t.getMessage());
            }
        };
    }

    @Override
    public void init(AppCompatActivity activity) {
        loadData(activity);
    }

    @Override
    public void filterData(String s) {

    }

    @Override
    public void fetchDataFromAPi() {
        ApiManager.getExchangeRate(apiCallback);
    }


    public interface Callback {
        void OnDataArrived(ArrayList<ExchangeRateModel> dataList, ExchangeRateResponse.AuthorModel model);
        void OnFailure(String msg);
    }
}
*/
