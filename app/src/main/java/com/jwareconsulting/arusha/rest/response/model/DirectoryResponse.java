package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.model.DirectoryModel;

import java.util.ArrayList;

/**
 * Created by root on 8/9/16.
 */
public class DirectoryResponse {

    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("data")
    ArrayList<DirectoryModel> dataList;

    public ArrayList<DirectoryModel> getDataList() {
        return dataList;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }
    @SerializedName("last_update")
    String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }

}
