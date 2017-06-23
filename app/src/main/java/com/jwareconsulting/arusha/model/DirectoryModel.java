package com.jwareconsulting.arusha.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 8/9/16.
 */
public class DirectoryModel {

    boolean isNotCatagory;   /// this is used for filtering purpose . in catagoryId map fragmment. this is not used for json parsing from rest api
    int position;

    @SerializedName("child")
    String child;

    @SerializedName("tid")
    String tid;

    @SerializedName("vid")
    String vid;

    @SerializedName("name")
    String name;

    @SerializedName("image")
    String image;

    @SerializedName("image-thumbnail")
    String thumnailImage;

    @SerializedName("image-medium")
    String mediumImage;

    @SerializedName("image-large")
    String largeImage;

    @SerializedName("image-providercategory-threex")
    String providerImage;
    private int iconResources;

    String catagoryTitle;

    public DirectoryModel() {
    }


    public String getCatagoryTitle() {
        return catagoryTitle;
    }

    public void setCatagoryTitle(String catagoryTitle) {
        this.catagoryTitle = catagoryTitle;
    }

    public String getTid() {
        return tid;
    }

    public String getVid() {
        return vid;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getThumnailImage() {
        return thumnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public String getProviderImage() {
        return providerImage;
    }

    public boolean hasChild() {
        if (child != null) {

            if (child.equals("true")) {
                return true;
            }

        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setThumnailImage(String thumnailImage) {
        this.thumnailImage = thumnailImage;
    }


    /**
     * @return true if it is catagoryId else return false
     */
    public boolean isCatagory() {
        return !isNotCatagory;
    }

    public void setCatagory(boolean notCatagory) {
        this.isNotCatagory = notCatagory;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setIconResources(int resources) {
        this.iconResources = resources;
    }

    public int getIconResources() {
        return iconResources;
    }
}
