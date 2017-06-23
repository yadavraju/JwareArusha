package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 8/9/16.
 */
public class EventResponseModel {

    @SerializedName("success")
    int success;

    @SerializedName("message")
    String message;


    @SerializedName("total_items")
    String totalItems;

    @SerializedName("data")
    ArrayList<EventData> dataList;

    @SerializedName("author")
    AuthorModel author;
    @SerializedName("last_update")
    String lastUpdate;


    public EventResponseModel() {
    }

    public String getTotalItems() {
        return totalItems;
    }


    public AuthorModel getAuthor() {
        return author;
    }

    public ArrayList<EventData> getDataList() {
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


    public class EventData {

        @SerializedName("id")
        String eventId;

        @SerializedName("title")
        String eventTitle;

        @SerializedName("body")
        String eventBody;

        @SerializedName("field_events_date_from")
        String eventFieldEventDateFrom;

        @SerializedName("field_events_date_to")
        String eventFieldEventDateTo;

        @SerializedName("field_events_time_from")
        String eventFieldEventTimeFrom;

        @SerializedName("field_events_time_to")
        String eventFieldEventTimeTo;

        @SerializedName("image")
        String eventImage;

        @SerializedName("image-thumbnail")
        String eventImageThumbnail;

        @SerializedName("image-medium")
        String eventImageMedium;

        @SerializedName("image-large")
        String eventImageLarge;

        @SerializedName("image-providercategory_threex")
        String eventImageProviderThreex;

        @SerializedName("field_events_address")
        String eventFieldAddress;

        @SerializedName("field_events_location")
        Location eventFieldLocation ;

        @SerializedName("field_events_rate")
        ArrayList<PriceData> field_events_rate;

        @SerializedName("field_trip_advisor")
        String eventTripAdvisorUrl;

        @SerializedName("field_facebook")
        String eventFbUrl;

        @SerializedName("field_twitter")
        String eventTwitterUrl;

        @SerializedName("field_youtube")
        String eventYoutubeUrl;

        @SerializedName("field_info")
        String eventHundredCharacterField;

        @SerializedName("field_events_email")
        String eventemailrUrl;

        @SerializedName("field_events_contact")
        String eventContacteUrl;

        @SerializedName("field_events_website")
        String eventwebUrl;

        public EventData() {

        }

        public String getEventId() {
            return eventId;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public String getEventBody() {
            return eventBody;
        }

        public String getEventFieldEventDateFrom() {
            return eventFieldEventDateFrom;
        }

        public String getEventFieldEventDateTo() {
            return eventFieldEventDateTo;
        }

        public String getEventFieldEventTimeFrom() {
            return eventFieldEventTimeFrom;
        }

        public String getEventFieldEventTimeTo() {
            return eventFieldEventTimeTo;
        }

        public String getEventImage() {
            return eventImage;
        }

        public String getEventImageThumbnail() {
            return eventImageThumbnail;
        }

        public String getEventImageMedium() {
            return eventImageMedium;
        }

        public String getEventImageLarge() {
            return eventImageLarge;
        }

        public String getEventImageProviderThreex() {
            return eventImageProviderThreex;
        }

        public String getEventFieldAddress() {
            return eventFieldAddress;
        }

        public Location getEventFieldLocation() {
            return eventFieldLocation;
        }

        public ArrayList<PriceData> getField_events_rate() {
            return field_events_rate;
        }

        public String getEventHundredCharacterField() {
            return eventHundredCharacterField;
        }

        public String getEventFbUrl() {
            return eventFbUrl;
        }

        public String getEventTwitterUrl() {
            return eventTwitterUrl;
        }

        public String getEventYoutubeUrl() {
            return eventYoutubeUrl;
        }

        public String getEventTripAdvisorUrl() {
            return eventTripAdvisorUrl;
        }

        public String getEventemailrUrl() {
            return eventemailrUrl;
        }

        public String getEventContacteUrl() {
            return eventContacteUrl;
        }

        public String getEventwebUrl() {
            return eventwebUrl;
        }
    }
    public class PriceData{

        @SerializedName("price")
        String eventPrice;

        public PriceData(){

        }

        public String getEventPrice() {
            return eventPrice;
        }
    }
    public class Location {
        @SerializedName("lat")
        String eventLat;

        @SerializedName("lon")
        String eventLon;

        public Location() {
        }

        public String getLat() {
            return eventLat;
        }

        public String getLon() {
            return eventLat;
        }
    }
    public class AuthorModel {

        @SerializedName("name")
        String eventAuthorName;

        @SerializedName("field_user_phone")
        String evenAuthorPhone;

        @SerializedName("field_user_address")
        UserLocation eventUserLocation;

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

        public UserLocation getEventUserLocation() {
            return eventUserLocation;
        }

        public String getEventModifiedDate() {
            return eventModifiedDate;
        }
    }

    public class UserLocation {

        @SerializedName("lat")
        String eventAuthorLat;

        @SerializedName("lon")
        String eventAuthorLon;

        @SerializedName("address")
        String eventAuthorAddress;

        public UserLocation()
        {

        }
    }

}
