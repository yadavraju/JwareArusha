package com.jwareconsulting.arusha.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 8/4/16.
 */
public class ExchangeRateModel {
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("field_buy_rate")
    String buy;
    @SerializedName("field_sell_rate")
    String sell;
    @SerializedName("author")
    AddressModel addressModel;

    @Expose
    @SerializedName("body")
    String body="123";

    public ExchangeRateModel() {
    }

    /**
     * @param currency
     * @param buy
     * @param sell
     */
    public ExchangeRateModel(String currency, String buy, String sell) {
        this.title = currency;
        this.buy = buy;
        this.sell = sell;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public String getBody() {
        return body;
    }
}
