package com.jwareconsulting.arusha.mvp.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.model.ExchangeRateModel;
import com.jwareconsulting.arusha.mvp.ExchangeRateMvp;
import com.jwareconsulting.arusha.mvp.MvpModel;
import com.jwareconsulting.arusha.mvp.presenter.ExchangeRatePresenter;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 8/4/16.
 */
public class ExchangeMvpModel extends MvpModel implements ExchangeRateMvp.Model {
    private Callback mCallBack;
    private retrofit2.Callback<ExchangeRateResponse> apiCallback;
    private AppCompatActivity activity;

    public ExchangeMvpModel() {
    }

    public ExchangeMvpModel(final ExchangeRatePresenter exchangeRatePresenter) {
        mCallBack = exchangeRatePresenter;
        apiCallback = new retrofit2.Callback<ExchangeRateResponse>() {
            @Override
            public void onResponse(Call<ExchangeRateResponse> call, Response<ExchangeRateResponse> response) {
                if (response.code() == 200) {
                    ExchangeRateResponse exchangeRateResponse = response.body();
                    if (exchangeRateResponse.isSuccess()) {
                        AppCache.cacheExchangeRate(activity,exchangeRateResponse);
                        mCallBack.OnDataArrived(exchangeRateResponse.getDataList(), exchangeRateResponse.getAuthorModel(),exchangeRateResponse.getExchangeRateTime());
                    } else {
                        mCallBack.OnFailure(exchangeRateResponse.getMessage());
                    }
                } else if (response.code() == 204) {
                    fetchFromCache(activity);
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
        this.activity=activity;
        loadData(activity);
//    fetchFromCache(activity);
    }
    @Override
    public void fetchDataFromAPi(Context context) {
        ApiManager.getExchangeRate(apiCallback, context);
    }

    @Override
    public void fetchFromCache(Context context) {
        if (AppCache.getCachedExchangeRate(context)!=null) {
            ExchangeRateResponse exchangeRateResponse = AppCache.getCachedExchangeRate(context);
            mCallBack.OnDataArrived(exchangeRateResponse.getDataList(), exchangeRateResponse.getAuthorModel(), exchangeRateResponse.getExchangeRateTime());
        }else {
            mCallBack.OnFailure(context.getString(R.string.no_cache_data));
        }
    }

    public interface Callback {
        void OnDataArrived(ArrayList<ExchangeRateModel> dataList, ExchangeRateResponse.AuthorModel model,String date);
        void OnFailure(String msg);
    }
}
