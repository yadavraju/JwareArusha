package com.jwareconsulting.arusha.map.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 9/12/16.
 */
public class Routes {

    @SerializedName("bounds")
    Bounds bounds;

    @SerializedName("overview_polyline")
    Overview_polyline overview_polyline;

    @SerializedName("legs")
    ArrayList <Legs> legs;

    public Bounds getBounds() {
        return bounds;
    }

    public Overview_polyline getOverview_polyline() {
        return overview_polyline;
    }

    public ArrayList<Legs> getLegs() {
        return legs;
    }
}
