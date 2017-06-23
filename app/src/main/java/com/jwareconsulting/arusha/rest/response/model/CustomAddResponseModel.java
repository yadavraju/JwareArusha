package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bittu on 9/12/16.
 */
public class CustomAddResponseModel {
    @SerializedName("success")
    int success;

    @SerializedName("message")
    String message;


    @SerializedName("total_items")
    String totalItems;

    @SerializedName("data")
    ArrayList<CustomeAddData> dataList;

    @SerializedName("author")
    AuthorModel author;


    public CustomAddResponseModel() {
    }

    public String getTotalItems() {
        return totalItems;
    }


    public AuthorModel getAuthor() {
        return author;
    }

    public ArrayList<CustomeAddData> getDataList() {
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

    public class CustomeAddData {

        @SerializedName("id")
        String customeAddId;
        @SerializedName("field_ads_url")
        String adsUrl;

        @SerializedName("title")
        String customeAddTitle;

        @SerializedName("body")
        String customeAddBody;

        @SerializedName("image")
        String customeAddImage;

        @SerializedName("image-thumbnail")
        String customeAddImageThumbnail;

        @SerializedName("image-medium")
        String customeAddImageMedium;

        @SerializedName("image-large")
        String customeAddImageLarge;

        @SerializedName("image-providercategory_threex")
        String customeAddImageProviderThreex;

        @SerializedName("field_ad_area")
        String customAddFieldArea;

        @SerializedName("field_trip_advisor")
        String customAddTripAdvisor;

        @SerializedName("field_facebook")
        String customAddFacebook;

        @SerializedName("field_twitter")
        String customAddTwitter;

        @SerializedName("field_youtube")
        String customAddYoutube;

        @SerializedName("field_ad_display_page")
        String customeFieldAdddisplayPage;

        public CustomeAddData() {

        }

        public String getAdsUrl() {
            return adsUrl;
        }

        public void setAdsUrl(String adsUrl) {
            this.adsUrl = adsUrl;
        }

        public String getCustomeAddId() {
            return customeAddId;
        }

        public String getCustomeAddTitle() {
            return customeAddTitle;
        }

        public String getCustomeAddBody() {
            return customeAddBody;
        }

        public String getCustomeAddImage() {
            return customeAddImage;
        }

        public String getCustomeAddImageThumbnail() {
            return customeAddImageThumbnail;
        }

        public String getCustomeAddImageMedium() {
            return customeAddImageMedium;
        }

        public String getCustomeAddImageLarge() {
            return customeAddImageLarge;
        }

        public String getCustomeAddImageProviderThreex() {
            return customeAddImageProviderThreex;
        }

        public String getCustomAddFieldArea() {
            return customAddFieldArea;
        }

        public String getCustomAddTripAdvisor() {
            return customAddTripAdvisor;
        }

        public String getCustomAddFacebook() {
            return customAddFacebook;
        }

        public String getCustomAddTwitter() {
            return customAddTwitter;
        }

        public String getCustomAddYoutube() {
            return customAddYoutube;
        }

        public String getCustomeFieldAdddisplayPage() {
            return customeFieldAdddisplayPage;
        }
    }

    public class AuthorModel {

        @SerializedName("name")
        String eventAuthorName;

        @SerializedName("field_user_phone")
        String evenAuthorPhone;

//        @SerializedName("field_user_address")
//        String eventUserLocation;

        @SerializedName("modified_date")
        String eventModifiedDate;


        public AuthorModel() {
        }

        public String getEventAuthorName() {
            return eventAuthorName;
        }

        public String getEvenAuthorPhone() {
            return evenAuthorPhone;
        }

//        public String getEventUserLocation() {
//            return eventUserLocation;
//        }

        public String getEventModifiedDate() {
            return eventModifiedDate;
        }
    }


}
