package com.jwareconsulting.arusha.rest;


import com.jwareconsulting.arusha.config.ApiConfig;
import com.jwareconsulting.arusha.rest.response.model.CustomAddResponseModel;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;
import com.jwareconsulting.arusha.rest.response.model.GetDirectionApiResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.ServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.rest.response.model.TemplateResponse;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anjulkhanal  on 2/25/16.
 */
public interface ApiInterface {
    String CHECKSUM = "3389dae361af79b04c9c8e7057f6045";
    String KEY_LAST_UPDATE = "";


    @GET(ApiConfig.URL_EXCHANGE_RATE)
    Call<ExchangeRateResponse> getExchangeRate(@Header("checksum") String checksum, @Query("last_update") String lastUpdate, @Query("lang") String language);

  /*  @GET(ApiConfig.BASE_URL)
    Call<TemplateResponse> getTemplateApi(@Header("checksum") String checksum,);
*/
    @GET(ApiConfig.URL_GET_ALL_SERVICE_PROVIDER_CATAGORY)
    Call<DirectoryResponse> getAllServiceProviderCatagory(@Header("checksum") String checksum, @Query("last_update") String lastUpdate, @Query("lang") String language);

    @GET(ApiConfig.URL_SERVICE_PROVIDER_BY_CATAGORY)
    Call<ServiceProviderResponse> getServiceProviderByCatagory(@Header("checksum") String checksum, @Path("id") String id, @Query("last_update") String lastUpdate, @Query("lang") String language);


    @GET(ApiConfig.URL_SERVICE_PROVIDER_BY_CATAGORY)
    Call<ServiceProviderResponse> getServiceProviderByCatagoryShowwAll(@Header("checksum") String checksum, @Path("id") String id,@Query("last_update") String lastUpdate, @Query("children") boolean value, @Query("lang") String language);

    @GET(ApiConfig.URL_SERVICE_PROVIDER_BY_CATAGORY)
    Call<SubCategoriesResponseModel> getSubCatagory(@Header("checksum") String checksum, @Path("id") String id, @Query("last_update") String lastUpdate, @Query("lang") String language);

    @GET(ApiConfig.URL_FETCH_MAP_DATA)
    Call<MapServiceProviderResponse> getServiceProviderForMap(@Header("checksum") String checksum, @Query("last_update") String lastUpdate, @Query("nopagination") String value, @Query("lang") String language);


    @GET(ApiConfig.URL_EVENT_LIST)
    Call<EventResponseModel> getEvent(@Header("checksum") String checksum, @Query("last_update") String lastUpdate, @Query("lang") String language);

    @GET(ApiConfig.URL_THINGTODO_LIST)
    Call<TopThingsToDoModel> getThingToDo(@Header("checksum") String checksum, @Query("last_update") String lastUpdate, @Query("lang") String language);

    @GET(ApiConfig.URL_CUSTOME_ADD)
    Call<CustomAddResponseModel> getCustomAdd(@Header("checksum") String checksum, @Query("last_update") String lastUpdate,@Query("nopagination") String value, @Query("lang") String language);

    @GET(ApiConfig.URL_DIRECTION)
    Call<GetDirectionApiResponse> getDirectionApi(@Query("origin") String fromDirection, @Query("destination") String toDirection);


}

