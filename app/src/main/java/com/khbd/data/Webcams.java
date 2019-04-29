package com.khbd.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import javakit.date.DateUtils;

import java.util.Date;

public class Webcams   {
    @JsonProperty
    private String id;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("videourl")
    private String Videourl;
    @JsonProperty("imageurl")
    private String Imageurl;
    @JsonProperty("categories")
    private String Categories;
    @JsonProperty("ianguages")
    private String Ianguages;
    @JsonProperty("pornstars")
    private String Pornstars;
    @JsonProperty("overview")
    private String Overview;
    @JsonProperty("datetime")
    private String Datetime;
    @JsonProperty("status")
    private Boolean Status;

    public Webcams() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getVideourl() {
        return Videourl;
    }

    public void setVideourl(String videourl) {
        Videourl = videourl;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public String getIanguages() {
        return Ianguages;
    }

    public void setIanguages(String ianguages) {
        Ianguages = ianguages;
    }

    public String getPornstars() {
        return Pornstars;
    }

    public void setPornstars(String pornstars) {
        Pornstars = pornstars;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
