package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjjul on 8/31/16.
 */
public class TopThingsToDoModel {
    @SerializedName("success")
    @Expose
    int success;

    @SerializedName("message")
    @Expose
    String message;


    @SerializedName("total_items")
    @Expose
    String totalItems;

    @SerializedName("data")
    @Expose
    ArrayList<ThingToData> dataList;

    @SerializedName("author")
    @Expose
    AuthorModel author;

    @SerializedName("last_update")
    String lastUpdate;

    public TopThingsToDoModel() {
    }

    public String getTotalItems() {
        return totalItems;
    }


    public AuthorModel getAuthor() {
        return author;
    }

    public ArrayList<ThingToData> getDataList() {
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


    public class ThingToData {

        @SerializedName("id")
        @Expose
        String thingToDoId;


        @SerializedName("title")
        @Expose
        String thingToDoTitle;

        @SerializedName("body")
        String body;

        @SerializedName("field_more_info_title")
        String moreInfo;

        @SerializedName("field_more_info_desc")
        String infoDescription;

        @SerializedName("field_top_trip_advisor")
        String tripAdvisior;

        @SerializedName("field_top_facebook")
        String fieldFacebook;

        @SerializedName("field_top_twitter")
        String fieldTwitter;

        @SerializedName("field_top_youtube")
        String fieldYoutube;

        @SerializedName("field_tenthingstodo_email")
        String fieldEmail;

        @SerializedName("field_tenthingstodo_website")
        String fieldWebsite;

        @SerializedName("field_tenthingstodo_location")
        @Expose
        Location thingToDoFieldLocation;

        @SerializedName("field_tenthingstodo_number")
        @Expose
        String thingToDoNumber;

        @SerializedName("image")
        @Expose
        String thingToDoImage;

        @SerializedName("image-thumbnail")
        @Expose
        String thingToDoImageThumbnail;

        @SerializedName("image-medium")
        @Expose
        String thingToDoImageMedium;

        @SerializedName("image-large")
        @Expose
        String thingToDoImageLarge;

        @SerializedName("image-providercategory_threex")
        @Expose
        String thingToDoImageProviderThreex;

        @SerializedName("field_tenthingstodo_address")
        @Expose
        String thingToDoFieldAddress;

        @SerializedName("field_tenthingstodo_price")
        String price;
        @SerializedName("field_opening_hours")
        @Expose
        ArrayList<FieldOpeningHour> fieldOpeningHour;


        public ThingToData() {

        }


        public String getBody() {
            return body;
        }

        public String getMoreInfo() {
            return moreInfo;
        }

        public String getInfoDescription() {
            return infoDescription;
        }

        public String getTripAdvisior() {
            return tripAdvisior;
        }

        public String getFieldFacebook() {
            return fieldFacebook;
        }

        public String getFieldTwitter() {
            return fieldTwitter;
        }

        public String getFieldYoutube() {
            return fieldYoutube;
        }

        public String getFieldEmail() {
            return fieldEmail;
        }

        public String getFieldWebsite() {

            return fieldWebsite;
        }

        public String getThingToDoId() {
            return thingToDoId;
        }

        public String getThingToDoTitle() {
            return thingToDoTitle;
        }

        public String getThingToDoNumber() {
            return thingToDoNumber;
        }

        public String getThingToDoImage() {
            return thingToDoImage;
        }

        public String getThingToDoImageThumbnail() {
            return thingToDoImageThumbnail;
        }

        public String getThingToDoImageMedium() {
            return thingToDoImageMedium;
        }

        public String getThingToDoImageLarge() {
            return thingToDoImageLarge;
        }

        public String getThingToDoImageProviderThreex() {
            return thingToDoImageProviderThreex;
        }

        public String getThingToDoFieldAddress() {
            return thingToDoFieldAddress;
        }

        public Location getThingToDoFieldLocation() {
            return thingToDoFieldLocation;
        }

        public ArrayList<FieldOpeningHour> getFieldOpeningHour() {
            return fieldOpeningHour;
        }


        public String getPrice() {
            return price;
        }
    }

    public class FieldOpeningHour {

        @SerializedName("days")
        @Expose
        String thingToDay;

        @SerializedName("timings")
        @Expose
        String thingToTimimg;

        public FieldOpeningHour() {

        }

        public String getThingToDay() {
            return thingToDay;
        }

        public String getThingToTimimg() {
            return thingToTimimg;
        }
    }

    public class Location {
        @SerializedName("lat")
        @Expose
        String thingToDoLat;

        @SerializedName("lon")
        @Expose
        String thingToDoLon;

        public Location() {
        }

        public String getLat() {
            return thingToDoLat;
        }

        public String getLon() {
            return thingToDoLat;
        }
    }

    public class AuthorModel {

        @SerializedName("name")
        @Expose
        String thingToDoAuthorName;

        @SerializedName("field_user_phone")
        @Expose
        String evenAuthorPhone;

        //        @SerializedName("field_user_address")
        @Expose
        UserLocation thingToDoUserLocation;

        @SerializedName("modified_date")
        @Expose
        String thingToDoModifiedDate;

        public AuthorModel() {
        }

        public String getThingToDoAuthorName() {
            return thingToDoAuthorName;
        }

        public String getEvenAuthorPhone() {
            return evenAuthorPhone;
        }

        public UserLocation getThingToDoUserLocation() {
            return thingToDoUserLocation;
        }

        public String getThingToDoModifiedDate() {
            return thingToDoModifiedDate;
        }
    }

    public class UserLocation {

        @SerializedName("lat")
        @Expose
        String thingToDoAuthorLat;

        @SerializedName("lon")
        @Expose
        String thingToDoAuthorLon;

        @SerializedName("address")
        @Expose
        String thingToDoAuthorAddress;

        public UserLocation() {

        }

        public String getThingToDoAuthorLat() {
            return thingToDoAuthorLat;
        }

        public String getThingToDoAuthorLon() {
            return thingToDoAuthorLon;
        }

        public String getThingToDoAuthorAddress() {
            return thingToDoAuthorAddress;
        }
    }


}
