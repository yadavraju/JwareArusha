package com.jwareconsulting.arusha.mvp.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.MapRecycleViewAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.model.ChildModel;
import com.jwareconsulting.arusha.mvp.DirectorySubcatagoryMvp;
import com.jwareconsulting.arusha.mvp.MvpModel;
import com.jwareconsulting.arusha.mvp.presenter.DirectorySubcatagoryPresenter;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.ui.fragment.MapMenufragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 8/4/16.
 */
public class DirectorySubcatagoryMvpModel extends MvpModel implements DirectorySubcatagoryMvp.Model {
    private Callback mCallBack;
    private retrofit2.Callback<SubCategoriesResponseModel> apiCallback;
    private ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList;
    ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> filteredDataList = new ArrayList<>();
    private boolean isFiltered;
    private String catagoryId;
    private AppCompatActivity activity;

    public DirectorySubcatagoryMvpModel() {
    }

    public DirectorySubcatagoryMvpModel(final DirectorySubcatagoryPresenter presenter) {
        mCallBack = presenter;
        apiCallback = new retrofit2.Callback<SubCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoriesResponseModel> call, Response<SubCategoriesResponseModel> response) {
                if (response.code() == 200) {
                    SubCategoriesResponseModel serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        String lastUpdate = serviceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheDirectorySubCategoryData(activity, serviceProviderResponse);
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
            public void onFailure(Call<SubCategoriesResponseModel> call, Throwable t) {
                mCallBack.OnFailure(t.getMessage());
            }
        };
    }////////////////////////////////////////////////////////////////////////

    /**
     * this has been used to load
     *
     * @param mapMenufragment
     */
    public DirectorySubcatagoryMvpModel(MapMenufragment mapMenufragment) {
        mCallBack = mapMenufragment;
        apiCallback = new retrofit2.Callback<SubCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoriesResponseModel> call, Response<SubCategoriesResponseModel> response) {
                if (response.code() == 200) {
                    SubCategoriesResponseModel serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        String lastUpdate = serviceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheDirectorySubCategoryData(activity, serviceProviderResponse);
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
            public void onFailure(Call<SubCategoriesResponseModel> call, Throwable t) {
                mCallBack.OnFailure(t.getMessage());
            }
        };


    }


    @Override
    public void init(AppCompatActivity activity, String catagoryId) {
        this.catagoryId = catagoryId;
        this.activity = activity;
        loadData(activity);
    }

    @Override
    public void filterData(String s) {
        filteredDataList.clear();
        if (dataList != null) {
            if (dataList.size() > 1) {
                for (int i = 0; i < dataList.size(); i++) {

                    String title = dataList.get(i).getSubCatName();
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
        }


    }

    @Override
    public String getTitle(int postion) {


        if (isFiltered()) {
            return filteredDataList.get(postion).getSubCatName();
        } else {

            return dataList.get(postion).getSubCatName();
        }

    }

    @Override
    public void fetchDetail(int position) {
    }

    private void setFilterFlag(boolean value) {
        isFiltered = value;
    }

    public boolean isFiltered() {
        return isFiltered;
    }

    @Override
    public void fetchDataFromAPi(Context context) {
        ApiManager.getSubCatagory(context, apiCallback, catagoryId);
    }

    @Override
    public void fetchFromCache(Context context) {
        if (AppCache.getCachedDirectorySubCatagoryData(context) != null) {
            SubCategoriesResponseModel subCategoriesResponseModel = AppCache.getCachedDirectorySubCatagoryData(context);
            dataList = new ArrayList<>();
            dataList = subCategoriesResponseModel.getDataList();
            setFilterFlag(false);
            mCallBack.OnDataArrived(dataList);
        } else {
            mCallBack.OnFailure(context.getString(R.string.no_cache_data));
        }

    }


    public interface Callback {
        void OnDataArrived(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList);

        void OnFailure(String msg);
    }


   /* public ChildModel getCatagoryId(int position) {
        ChildModel model = new ChildModel();
        if (isFiltered()) {
                model.setCatagoryTitle(filteredDataList.get(position).getSubCatName());
//                DIrectorySubCatagoryFragment.setTitle(filteredDataList.get(position).getName());
            }
//            model.setCatagoryId(filteredDataList.get(position).getTid());
//            model.setChild(filteredDataList.get(position).hasChild());
//            model.setSearchedText(searchedText);
//            return model;
        } else {
//            DIrectorySubCatagoryFragment.setTitle(dataList.get(position).getName());
            model.setCatagoryTitle(dataList.get(position).getName());
            model.setCatagoryId(dataList.get(position).getTid());
            model.setChild(dataList.get(position).hasChild());
//            model.setSearchedText(searchedText);
            return model;
        }

    }*/


}
