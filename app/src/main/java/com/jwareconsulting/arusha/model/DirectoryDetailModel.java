package com.jwareconsulting.arusha.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 8/17/16.
 */
public class DirectoryDetailModel {
    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("date")
    String date;


    @SerializedName("image")
    String image;

    @SerializedName("image-thumbnail")
    String thumbImage;

    @SerializedName("image-medium")
    String mediumImage;

    @SerializedName("image-large")
    String largeImage;

    @SerializedName("image-providercategory_threex")
    String image3x;




}
