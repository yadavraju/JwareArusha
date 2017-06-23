package com.jwareconsulting.arusha.map.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anjulkhanal on 9/12/16.
 */
public class Overview_polyline {
    @SerializedName("points")
    String points;

    public String getPoints() {
        return points;
    }
}
