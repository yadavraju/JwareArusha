package com.jwareconsulting.arusha.common;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;
import com.jwareconsulting.arusha.rest.response.model.ServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 9/6/16.
 */
public class AppCache {


    public static ArrayList<ServiceProviderModel> mapDataCatagoryDataList = new ArrayList<>();

    public static ArrayList<ServiceProviderModel> getMapDataCatagoryDataList() {
        return mapDataCatagoryDataList;
    }

    public static void setMapDataCatagoryDataList(ArrayList<ServiceProviderModel> dataList) {
        mapDataCatagoryDataList = dataList;
    }

    public static ExchangeRateResponse getCachedExchangeRate(Context context) {
            String json = PrefenceUtil.getExchangeRateData(context);
            Gson gson = new Gson();
            ExchangeRateResponse exchangeRateResponse = gson.fromJson(json, ExchangeRateResponse.class);
            return exchangeRateResponse;
    }

    public static void cacheExchangeRate(AppCompatActivity activity, ExchangeRateResponse exchangeRateResponse) {
        String lastUpdate = exchangeRateResponse.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(activity, PrefenceUtil.KEY_SYNC_EXCHANGE_RATE, lastUpdate);
        }
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
       Gson gson= new Gson();
        String json = gson.toJson(exchangeRateResponse, ExchangeRateResponse.class);
        PrefenceUtil.storeExchageRateData(activity, json);
    }

    public static EventResponseModel getCachedEventRate(Context context) {
        String json = PrefenceUtil.getEventData(context);
        Gson gson = new Gson();
        EventResponseModel eventResponseModel = gson.fromJson(json, EventResponseModel.class);
        return eventResponseModel;
    }

    public static void cacheEventRate(Context context, EventResponseModel eventResponseModel) {
        String lastUpdate = eventResponseModel.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(context, PrefenceUtil.KEY_SYNC_EVENT_LIST, lastUpdate);
        }
        Gson gson = new Gson();
        String json = gson.toJson(eventResponseModel, EventResponseModel.class);
        PrefenceUtil.setEventData(context, json);

    }


    public static void cacheThingsToDoData(Context context, TopThingsToDoModel topThingsToDoModel) {
        String lastUpdate = topThingsToDoModel.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(context, PrefenceUtil.KEY_SYNC_THINGS_TODO, lastUpdate);
        }
        Gson gson = new Gson();
        String json = gson.toJson(topThingsToDoModel, TopThingsToDoModel.class);
        PrefenceUtil.setThingToDoData(context, json);
    }

    public static TopThingsToDoModel getCachedThingsToDoData(Context context) {
        String json = PrefenceUtil.getThingsToDoData(context);
        Gson gson = new Gson();
        TopThingsToDoModel topThingsToDoModel = gson.fromJson(json, TopThingsToDoModel.class);
        return topThingsToDoModel;
    }

    public static DirectoryResponse getCachedDirectoryData(Context context) {
        String json = PrefenceUtil.getDirectorData(context);
        Gson gson = new Gson();
        DirectoryResponse directoryResponse = gson.fromJson(json, DirectoryResponse.class);
        return directoryResponse;
    }

    public static void cacheDirectoryData(Context context, DirectoryResponse directoryResponse) {
        String lastUpdate = directoryResponse.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(context, PrefenceUtil.KEY_SYNC_ALL_SERVICE_PROVIDER_BY_CATAGORY, lastUpdate);
        }
        Gson gson = new Gson();
        String json = gson.toJson(directoryResponse, DirectoryResponse.class);
        PrefenceUtil.setDirectoryData(context, json);

    }

    public static ServiceProviderResponse getCachedDirectoryDetailData(Context context, String id) {
        String json = PrefenceUtil.getDirectoryDetailData(context, id);
        Gson gson = new Gson();
        ServiceProviderResponse serviceProviderResponse = gson.fromJson(json, ServiceProviderResponse.class);
        return serviceProviderResponse;
    }

    public static void cacheDirectoryDetailData(Context context, ServiceProviderResponse serviceProviderResponse, String id) {
        String lastUpdate = serviceProviderResponse.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(context, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_BY_CATAGORY + id, lastUpdate);
        }
        Gson gson = new Gson();
        String json = gson.toJson(serviceProviderResponse, ServiceProviderResponse.class);
        PrefenceUtil.setDirectoryDetailData(context, json, id);

    }

    public static SubCategoriesResponseModel getCachedDirectorySubCatagoryData(Context context) {
        String json = PrefenceUtil.getDirectorySubCategoryData(context);
        Gson gson = new Gson();
        SubCategoriesResponseModel subCategoriesResponseModel = gson.fromJson(json, SubCategoriesResponseModel.class);
        return subCategoriesResponseModel;
    }

    public static void cacheDirectorySubCategoryData(Context context, SubCategoriesResponseModel subCategoriesResponseModel) {
        String lastUpdate = subCategoriesResponseModel.getLastUpdate();
        if (lastUpdate != null) {
            PrefenceUtil.storeLastUpdate(context, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_BY_SUB_CATAGORY, lastUpdate);
        }
        Gson gson = new Gson();
        String json = gson.toJson(subCategoriesResponseModel, SubCategoriesResponseModel.class);
        PrefenceUtil.setDirectorySubCategorylData(context, json);
    }
}
