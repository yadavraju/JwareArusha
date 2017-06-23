package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.map.model.Routes;

import java.util.ArrayList;

public class GetDirectionApiResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("routes")
    private ArrayList<Routes> routes;



    public String getStatus() {
        return status;
    }


    public ArrayList<Routes> getRoutes() {
        return routes;
    }

    public boolean isSuccess() {
        if (status.equals("OK")) {
            return true;
        }
        return false;

    }

}