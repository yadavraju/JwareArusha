package com.jwareconsulting.arusha.map.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anjulkhanal  on 9/12/16.
 */
public class Legs {

    @SerializedName("distance")
    Distance distance;


    @SerializedName("duration")
    Duration duration;

    @SerializedName("end_address")
    String endAddress;

    @SerializedName("start_address")
    String startAddress;

    @SerializedName("start_location")
    StartLocation startLocation;

    @SerializedName("end_location")
    EndLocation endLocation;

    public class Duration {
      @SerializedName("text")
        String text;

        @SerializedName("value")
        String value;

        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }
    }
    public class Distance {
        @SerializedName("text")
        String text;

        @SerializedName("value")
        String value;

        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }
    }


    public class EndLocation {
        @SerializedName("lat")
        String lat;
        @SerializedName("lng")
        String lng;

        public Double getLat() {
            if(lat!=null){
                return Double.parseDouble(lat);
            }
            return null;
        }

        public Double getLng() {
            if(lng!=null){
                return Double.parseDouble(lng);
            }
            return null;
        }
    }

    public class StartLocation {
        @SerializedName("lat")
        String lat;
        @SerializedName("lng")
        String lng;

        public Double getLat() {
            if(lat!=null){
                return Double.parseDouble(lat);
            }
            return null;
        }

        public Double getLng() {
            if(lng!=null){
                return Double.parseDouble(lng);
            }
            return null;
        }
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }
}
