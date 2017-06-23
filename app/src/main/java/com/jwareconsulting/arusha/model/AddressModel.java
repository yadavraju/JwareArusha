package com.jwareconsulting.arusha.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anjulkhanal on 8/7/16.
 */
public class AddressModel {

    @SerializedName("lat")
    String lat;
    @SerializedName("lon")
    String lon;
    @SerializedName("address")
    String address;

    public AddressModel() {
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }
}
