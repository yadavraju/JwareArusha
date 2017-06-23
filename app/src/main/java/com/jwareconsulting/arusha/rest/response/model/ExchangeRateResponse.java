package com.jwareconsulting.arusha.rest.response.model;

import com.google.gson.annotations.SerializedName;
import com.jwareconsulting.arusha.model.AddressModel;
import com.jwareconsulting.arusha.model.ExchangeRateModel;

import java.util.ArrayList;

/**
 * Created by root on 8/4/16.
 */
public class ExchangeRateResponse {

    @SerializedName("message")
    String message;

    @SerializedName("success")
    int success;

    @SerializedName("exchange_rate_time")
    String exchangeRateTime;

    @SerializedName("data")
    ArrayList<ExchangeRateModel> dataList;

    @SerializedName("author")
    AuthorModel authorModel;

    public String getMessage() {
        return message;
    }

    public String getExchangeRateTime() {
        return exchangeRateTime;
    }

    public boolean isSuccess() {
        if (success == 1)
            return true;
        return false;
    }

    public ArrayList<ExchangeRateModel> getDataList() {
        return dataList;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public class AuthorModel {
        @SerializedName("name")
        String name;

        @SerializedName("field_user_phone")
        String userPhone;

        @SerializedName("field_user_address")
        AddressModel userAddress;

        @SerializedName("modified_date")
        String modifiedDate;



        public AuthorModel() {
        }

        public String getName() {
            return name;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public AddressModel getUserAddress() {
            return userAddress;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }


    }

    @SerializedName("last_update")
    String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }
}
