package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 8/9/16.
 */
public class TemplateResponse {

    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("data")
    ArrayList<TemplateModel> dataList;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }

    public class TemplateModel{

    }
}
