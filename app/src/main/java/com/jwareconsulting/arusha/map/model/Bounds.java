package com.jwareconsulting.arusha.map.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.common.ValidationUtils;

/**
 * Created by anjulkhanal  on 9/12/16.
 */
public class Bounds {

    @SerializedName("northeast")
    public NorthEast northEast;

    @SerializedName("southwest")
    public SouthEast southEast;


    public class NorthEast {
        @SerializedName("lat")
        String lat;

        @SerializedName("lng")
        String lng;


        public Double getLat() {
            if (ValidationUtils.isEmpty(lat)) {
                return null;
            }

            return Double.parseDouble(lat);
        }

        public Double getLng() {
            if (ValidationUtils.isEmpty(lng)) {
                return null;
            }

            return Double.parseDouble(lng);
        }
    }

    public class SouthEast {
        @SerializedName("lat")
        String lat;

        @SerializedName("lng")
        String lng;

        public Double getLat() {
            if (ValidationUtils.isEmpty(lat)) {
                return null;
            }

            return Double.parseDouble(lat);
        }

        public Double getLng() {
            if (ValidationUtils.isEmpty(lng)) {
                return null;
            }

            return Double.parseDouble(lng);
        }

    }

    public Bounds.NorthEast getNorthEast() {
        return northEast;
    }

    public SouthEast getSouthEast() {
        return southEast;
    }
}
