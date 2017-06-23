package com.jwareconsulting.arusha.mvp.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.mvp.DirectoryDetailMvp;
import com.jwareconsulting.arusha.mvp.MvpModel;
import com.jwareconsulting.arusha.mvp.presenter.DirectoryDetailPresenter;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.ServiceProviderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 8/4/16.
 */
public class DirectoryDetailMvpModel extends MvpModel implements DirectoryDetailMvp.Model {
    private Callback mCallBack;
    private retrofit2.Callback<ServiceProviderResponse> apiCallback;
    private ArrayList<ServiceProviderModel> dataList;
    ArrayList<ServiceProviderModel> filteredDataList = new ArrayList<>();
    private boolean isFiltered;
    private String catagoryId;
    int position;
    private AppCompatActivity activity;

    public DirectoryDetailMvpModel() {
    }

    public DirectoryDetailMvpModel(final DirectoryDetailPresenter presenter) {
        mCallBack = presenter;

        apiCallback = new retrofit2.Callback<ServiceProviderResponse>() {

            @Override
            public void onResponse(Call<ServiceProviderResponse> call, Response<ServiceProviderResponse> response) {

                if (response.code() == 200) {
                    ServiceProviderResponse serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        String lastUpdate = serviceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheDirectoryDetailData(activity, serviceProviderResponse, catagoryId);
                            //PrefenceUtil.storeLastUpdate(activity,PrefenceUtil.KEY_SYNC_EXCHANGE_RATE,lastUpdate);
                        }
                        dataList = new ArrayList<>();
                        dataList = serviceProviderResponse.getDataList();
                        setFilterFlag(false);
                        mCallBack.OnDataArrived(dataList);

                    } else {
                        mCallBack.OnFailure(serviceProviderResponse.getMessage());
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
            public void onFailure(Call<ServiceProviderResponse> call, Throwable t) {
                mCallBack.OnFailure(t.getMessage());
            }
        };
    }


    @Override
    public void init(AppCompatActivity activity, String catagoryId, int position) {
        this.catagoryId = catagoryId;
        this.position = position;
        this.activity = activity;
        loadData(activity);
    }

    @Override
    public void filterData(String s) {
        filteredDataList.clear();
        try {
            if (dataList.size() > 1) {
                for (int i = 0; i < dataList.size(); i++) {
                    String title = dataList.get(i).getTitle();
                    if (title != null) {
                        if (title.toLowerCase().contains(s.toLowerCase())) {
                            filteredDataList.add(dataList.get(i));
                        }
                    }
                    if (TextUtils.isEmpty(s) || s.trim().length() < 1) {
                        mCallBack.OnDataArrived(dataList);
                        setFilterFlag(false);
                    } else {
                        mCallBack.OnDataArrived(filteredDataList);
                        setFilterFlag(true);
                    }
                }
            }
        } catch (Exception e) {
            AppLog.printStackStrace(e);
        }


    }


    private void setFilterFlag(boolean value) {
        isFiltered = value;
    }

    public boolean isFiltered() {
        return isFiltered;
    }

    @Override
    public void fetchDataFromAPi(Context context) {
        if (position == 0) {
            ApiManager.getServiceProviderByCatagoryForShowAllList(context, apiCallback, catagoryId);
        } else {
            ApiManager.getServiceProviderByCatagory(context, apiCallback, catagoryId);
        }
    }

    @Override
    public void fetchFromCache(Context context) {
        if (AppCache.getCachedDirectoryDetailData(context, catagoryId) != null) {
            ServiceProviderResponse serviceProviderResponse = AppCache.getCachedDirectoryDetailData(context, catagoryId);
            dataList = new ArrayList<>();
            dataList = serviceProviderResponse.getDataList();
            setFilterFlag(false);
            mCallBack.OnDataArrived(dataList);

        } else {
            mCallBack.OnFailure(context.getString(R.string.no_cache_data));
        }
    }


    public interface Callback {
        void OnDataArrived(ArrayList<ServiceProviderModel> dataList);

        void OnFailure(String msg);
    }
}
