package com.jwareconsulting.arusha.model;

/**
 * Created by root on 9/27/16.
 */

public class AutoCompleteModel {


    String title;
    String latlog;

    public AutoCompleteModel(String title, String latlog) {
        this.title = title;
        this.latlog = latlog;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatlog() {
        return latlog;
    }

    public void setLatlog(String latlog) {
        this.latlog = latlog;
    }
}
