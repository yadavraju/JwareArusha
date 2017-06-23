package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.model.ServiceProviderModel;

import java.util.ArrayList;

/**
 * Created by root on 8/9/16.
 */
public class ServiceProviderResponse {

    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("data")
    ArrayList<ServiceProviderModel> dataList;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }

    public ArrayList<ServiceProviderModel> getDataList() {
        return dataList;
    }


    @SerializedName("last_update")
    String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }}
