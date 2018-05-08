package com.rjt.android.myapplication.model.pojo_BoundCoordinates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("boundary")
    @Expose
    private String boundary;
    @SerializedName("geo_center_latitude")
    @Expose
    private String geoCenterLatitude;
    @SerializedName("geo_center_longitude")
    @Expose
    private String geoCenterLongitude;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getGeoCenterLatitude() {
        return geoCenterLatitude;
    }

    public void setGeoCenterLatitude(String geoCenterLatitude) {
        this.geoCenterLatitude = geoCenterLatitude;
    }

    public String getGeoCenterLongitude() {
        return geoCenterLongitude;
    }

    public void setGeoCenterLongitude(String geoCenterLongitude) {
        this.geoCenterLongitude = geoCenterLongitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
