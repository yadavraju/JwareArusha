package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.model.MapServiceProviderModel;

import java.util.ArrayList;

/**
 * Created by root on 8/9/16.
 */
public class MapServiceProviderResponse {

    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("data")
    ArrayList<MapServiceProviderModel> dataList;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }

    public ArrayList<MapServiceProviderModel> getDataList() {
        return dataList;
    }

    @SerializedName("last_update")
    String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }
}
