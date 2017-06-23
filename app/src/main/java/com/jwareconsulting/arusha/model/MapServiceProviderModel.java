package com.jwareconsulting.arusha.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 8/10/16.
 */
public class MapServiceProviderModel {

    public MapServiceProviderModel() {
    }

    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("date")
    String date;

    @SerializedName("field_serviceprovider_location")
    Location location;

    @SerializedName("field_opening_hours")
    ArrayList<OpeningHour> openingHour;

    @SerializedName("field_serviceprovider_number")
    String serviceProvider;

    @SerializedName("field_skype_id")
    String skypeId;

    @SerializedName("field_whatsapp_id")
    String whatsappId;

    @SerializedName("field_email_id")
    String emailId;

    @SerializedName("field_website_url")
    String webUrl;

    @SerializedName("field_serviceprovider_address")
    String address;

    @SerializedName("field_trip_advisor_url")
    String tripAdvisiorURl;

    @SerializedName("field_facebook_url")
    String facebookUrl;

    @SerializedName("field_twitter_url")
    String twitterUrl;

    @SerializedName("field_youtube_url")
    String youtubeUrl;


    @SerializedName("image")
    String image;

    @SerializedName("image-thumbnail")
    String imageThumnail;

    @SerializedName("image-medium")
    String imageMedium;

    @SerializedName("image-large")
    String imageLarge;

    @SerializedName("image-providercategory_threex")
    String image3x;

    @SerializedName("field_provider_category")
    ArrayList<Catagory> catagory;


    String additionalDescription = "Contact on email or Facebook for hours of avaliability";
    String tripadvisiorRank = "";

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<OpeningHour> getOpeningHour() {
        return openingHour;
    }

    public String getImage() {
        return image;
    }

    public String getImageThumnail() {
        return imageThumnail;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public String getImage3x() {
        return image3x;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public ArrayList<Catagory> getCatagory() {
        return catagory;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public String getWhatsappId() {
        return whatsappId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getTripAdvisiorURl() {
        return tripAdvisiorURl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }


    public class Location {
        @SerializedName("lat")
        String lat;

        @SerializedName("lon")
        String lon;

        public Location() {
        }

        public String getLat() {
            return lat;
        }

        public String getLon() {
            return lon;
        }
    }

    public class OpeningHour {
        @SerializedName("days")
        String days;

        @SerializedName("timings")
        String timing;

        public OpeningHour() {
        }

        public String getDays() {
            return days;
        }

        public String getTiming() {
            return timing;
        }
    }

    public class Catagory {
        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;

        public Catagory() {
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
