package com.jwareconsulting.arusha.mvp.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.model.ChildModel;
import com.jwareconsulting.arusha.model.DirectoryModel;
import com.jwareconsulting.arusha.model.MapServiceProviderModel;
import com.jwareconsulting.arusha.mvp.DirectoryMvp;
import com.jwareconsulting.arusha.mvp.MvpModel;
import com.jwareconsulting.arusha.mvp.presenter.DirectoryPresenter;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;
import com.jwareconsulting.arusha.ui.fragment.DIrectorySubCatagoryFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 8/4/16.
 */
public class DirectoryMvpModel extends MvpModel implements DirectoryMvp.Model {
    ArrayList<DirectoryModel> filteredDataList = new ArrayList<>();
    private String TAG = DirectoryMvpModel.class.getSimpleName();
    private Callback mCallBack;
    private retrofit2.Callback<DirectoryResponse> apiCallback;
    private ArrayList<DirectoryModel> dataList;
    private boolean isFiltered;
    private AppCompatActivity activity;
    private ArrayList<MapServiceProviderModel> mapServiceProviderModelArrayList;
    private String searchedText = "";

    public DirectoryMvpModel() {
    }

    public DirectoryMvpModel(final DirectoryPresenter presenter) {
        mCallBack = (Callback) presenter;
        apiCallback = new retrofit2.Callback<DirectoryResponse>() {
            @Override
            public void onResponse(Call<DirectoryResponse> call, Response<DirectoryResponse> response) {
                if (response.code() == 200) {
                    DirectoryResponse directoryResponse = response.body();
                    if (directoryResponse.isSuccess()) {
                        String lastUpdate = directoryResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheDirectoryData(activity, directoryResponse);
                            //PrefenceUtil.storeLastUpdate(activity, lastUpdate, PrefenceUtil.KEY_SYNC_EXCHANGE_RATE);
                        }
                        dataList = new ArrayList<>();
                        dataList = directoryResponse.getDataList();
                        setFilterFlag(false);
                        mCallBack.OnDataArrived(dataList);
                    } else {
                        mCallBack.OnFailure(directoryResponse.getMessage());
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
            public void onFailure(Call<DirectoryResponse> call, Throwable t) {
                mCallBack.OnFailure(t.getMessage());
            }
        };
    }

    @Override
    public void init(AppCompatActivity activity) {
        this.activity = activity;
        loadData(activity);
        if (PrefenceUtil.hasMapServiceProvider(activity)) {
//            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            String jsonString = PrefenceUtil.getMapServiceProvider(activity);
            MapServiceProviderResponse serviceProviderResponse = new Gson().fromJson(jsonString, MapServiceProviderResponse.class);
            mapServiceProviderModelArrayList = serviceProviderResponse.getDataList();
        } else {
            AppLog.d(TAG, "doesnot have  serviceprovider");
        }
    }

    @Override
    public void filterData(String s) {
        filteredDataList.clear();
        if (dataList != null) {
            boolean isEmptySearch = TextUtils.isEmpty(s) || s.trim().length() < 1;
            if (!isEmptySearch) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                        filteredDataList.add(dataList.get(i));
                    }
                    setFilterFlag(true);
                }
                if (mapServiceProviderModelArrayList != null) {
                    if (mapServiceProviderModelArrayList.size() > 0) {
                        for (int j = 0; j < mapServiceProviderModelArrayList.size(); j++) {
                            String catagoryName = mapServiceProviderModelArrayList.get(j).getCatagory().get(0).getName();
                            String title = mapServiceProviderModelArrayList.get(j).getTitle();
                            searchedText = title;
                            if (title.toLowerCase().contains(s.toLowerCase())) {
                                DirectoryModel directoryModel = new DirectoryModel();
                                directoryModel.setName(title);
                                directoryModel.setCatagory(true);
                                directoryModel.setCatagoryTitle(catagoryName);
//                                directoryModel.setPosition(j);
                                directoryModel.setTid(mapServiceProviderModelArrayList.get(j).getCatagory().get(0).getId());
                                filteredDataList.add(directoryModel);
                            }
                        }
                    }

                }
                mCallBack.OnDataArrived(filteredDataList);
            } else {
                setFilterFlag(false);
                mCallBack.OnDataArrived(dataList);
            }
        }
    }

    @Override
    public ChildModel getCatagoryId(int position) {
        ChildModel model = new ChildModel();
        if (isFiltered()) {
            boolean isFromMapServiceProvider = filteredDataList.get(position).isCatagory();
            if (!isFromMapServiceProvider) {
                model.setIsCatagory(filteredDataList.get(position).isCatagory());
                model.setCatagoryTitle(filteredDataList.get(position).getCatagoryTitle());
//                DIrectorySubCatagoryFragment.setTitle(filteredDataList.get(position).getCatagoryTitle());
            } else {
                model.setCatagoryTitle(filteredDataList.get(position).getName());
//                DIrectorySubCatagoryFragment.setTitle(filteredDataList.get(position).getName());
            }
            model.setCatagoryId(filteredDataList.get(position).getTid());
            model.setChild(filteredDataList.get(position).hasChild());
//            model.setSearchedText(searchedText);
            return model;
        } else {
//            DIrectorySubCatagoryFragment.setTitle(dataList.get(position).getName());
            model.setCatagoryTitle(dataList.get(position).getName());
            model.setCatagoryId(dataList.get(position).getTid());
            model.setChild(dataList.get(position).hasChild());
//            model.setSearchedText(searchedText);
            return model;
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
        ApiManager.getAllServiceProviderCatagory(apiCallback, context);
    }

    @Override
    public void fetchFromCache(Context context) {
        if (AppCache.getCachedDirectoryData(context) != null) {
            DirectoryResponse directoryResponse = AppCache.getCachedDirectoryData(context);
            dataList = new ArrayList<>();
            dataList = directoryResponse.getDataList();
            setFilterFlag(false);
            mCallBack.OnDataArrived(dataList);
        } else {
            mCallBack.OnFailure(context.getString(R.string.no_cache_data));
        }

    }


    public interface Callback {
        void OnDataArrived(ArrayList<DirectoryModel> dataList);

        void OnFailure(String msg);
    }
}
