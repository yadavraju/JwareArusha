package com.jwareconsulting.arusha.rest;


import android.content.Context;

import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.config.ApiConfig;
import com.jwareconsulting.arusha.rest.response.model.CustomAddResponseModel;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;
import com.jwareconsulting.arusha.rest.response.model.GetDirectionApiResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.ServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by anjulkhanal on 2/28/16.
 */
public class ApiManager {
    public static final String TAG = "ApiManager";

    public static void getExchangeRate(Callback<ExchangeRateResponse> callback, Context context) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<ExchangeRateResponse> call = apiInterface.getExchangeRate(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_EXCHANGE_RATE), PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

   /*public static void getTemplateURl(Callback<TemplateResponse> callback){
       ApiInterface apiInterface =
               RestServiceGenerator.createService(ApiInterface.class);

       Call<TemplateResponse> call = apiInterface.getTemplateApi(ApiInterface.CHECKSUM);
       call.enqueue(callback);
   }*/

    //todo sync & cache

    /**
     * \
     * suruko load huney bela use gareko directory data
     *
     * @param callback
     * @param context
     */
    public static void getAllServiceProviderCatagory(Callback<DirectoryResponse> callback, Context context) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<DirectoryResponse> call = apiInterface.getAllServiceProviderCatagory(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context,
                PrefenceUtil.KEY_SYNC_ALL_SERVICE_PROVIDER_BY_CATAGORY), PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

    /**
     * directory detail ma use bhako cha  // syncing and caching
     *
     * @param context
     * @param callback
     * @param catagoryId
     */
    public static void getServiceProviderByCatagory(Context context, Callback<ServiceProviderResponse> callback, String catagoryId) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);

        Call<ServiceProviderResponse> call = apiInterface.getServiceProviderByCatagory(ApiInterface.CHECKSUM, catagoryId, PrefenceUtil.getLastUpdatedDate(context,
                PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_BY_CATAGORY + catagoryId)), PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

    //todo sync & cache
    public static void getServiceProviderByCatagoryForShowAllList(Context context, Callback<ServiceProviderResponse> callback, String catagoryId) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);

        Call<ServiceProviderResponse> call = apiInterface.getServiceProviderByCatagoryShowwAll(ApiInterface.CHECKSUM, catagoryId, PrefenceUtil.getLastUpdatedDate(context,
                PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_BY_CATAGORY_SHOWALL_LIST)), false, PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

    //todo sync & cache
    public static void getSubCatagory(Context context, Callback<SubCategoriesResponseModel> callback, String catagoryId) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);

        Call<SubCategoriesResponseModel> call = apiInterface.getSubCatagory(ApiInterface.CHECKSUM, catagoryId, PrefenceUtil.getLastUpdatedDate(context,
                PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_BY_SUB_CATAGORY)), PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

    //todo sync & cache
    public static void getServiceProviderForMap(Callback<MapServiceProviderResponse> callback, Context context) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<MapServiceProviderResponse> call = apiInterface.getServiceProviderForMap(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context,
                PrefenceUtil.KEY_SYNC_SERVICE_PROVIDER_MAP), "1", PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }


    public static void getRoutingDirectionApi(Callback<GetDirectionApiResponse> callback, String origin, String destination) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class, ApiConfig.BASE_URL_MAP);
        Call<GetDirectionApiResponse> call = apiInterface.getDirectionApi(origin, destination);
        call.enqueue(callback);
    }


    public static void getEventApi(Context context, Callback<EventResponseModel> eventResponseModelCallback) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<EventResponseModel> call = apiInterface.getEvent(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_EVENT_LIST), PrefenceUtil.getUserLanguage(context));
        call.enqueue(eventResponseModelCallback);
    }


    public static void getTopThingsTodo(Context context, Callback<TopThingsToDoModel> callback) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<TopThingsToDoModel> call = apiInterface.getThingToDo(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_THINGS_TODO), PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }

    public static void getCostumAdd(Context context, Callback<CustomAddResponseModel> callback) {
        ApiInterface apiInterface =
                RestServiceGenerator.createService(ApiInterface.class);
        Call<CustomAddResponseModel> call = apiInterface.getCustomAdd(ApiInterface.CHECKSUM, PrefenceUtil.getLastUpdatedDate(context, PrefenceUtil.KEY_SYNC_URL_COSUTM_ADD), "1", PrefenceUtil.getUserLanguage(context));
        call.enqueue(callback);
    }


}
