package com.jwareconsulting.arusha.model;

/**
 * Created by root on 8/17/16.
 */
public class ChildModel {
    String catagoryId;
    String searchedText;
    boolean child;
    boolean isCatagory;
    String searchKeyWord;
    String catagoryTitle;

    public ChildModel() {
    }


    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public void setCatagoryId(String catagory) {
        this.catagoryId = catagory;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public String getCatagory() {
        return catagoryId;
    }

    public boolean hasChild() {
        return child;
    }

    public boolean getCatagoryId() {
        return isCatagory;
    }

    public void setIsCatagory(boolean catagory) {
        isCatagory = catagory;
    }

    public String getSearchedText() {
        return searchedText;
    }

    public void setSearchedText(String searchedText) {
        this.searchedText = searchedText;
    }

    public String getCatagoryTitle() {
        return catagoryTitle;
    }

    public void setCatagoryTitle(String catagoryTitle) {
        this.catagoryTitle = catagoryTitle;
    }
}
