package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bittu on 9/21/16.
 */
public class SubCategoriesResponseModel {
    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("data")
    ArrayList<DirectorySubCAtegoriesModel> dataList;

    @SerializedName("last_update")
    String lastUpdate;

    public ArrayList<DirectorySubCAtegoriesModel> getDataList() {
        return dataList;
    }

    public String getMessage() {
        return message;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }

    public class DirectorySubCAtegoriesModel {
        @SerializedName("tid")
        String subCatId;

        @SerializedName("name")
        String subCatName;

        public String getSubCatId() {
            return subCatId;
        }

        public String getSubCatName() {
            return subCatName;
        }
    }
}
