package com.rjt.android.myapplication;

public class ViewPort {
    double ne_lat, ne_lng, sw_lat, sw_lng;

    public double getNe_lat() {
        return ne_lat;
    }

    public void setNe_lat(double ne_lat) {
        this.ne_lat = ne_lat;
    }

    public double getNe_lng() {
        return ne_lng;
    }

    public void setNe_lng(double ne_lng) {
        this.ne_lng = ne_lng;
    }

    public double getSw_lat() {
        return sw_lat;
    }

    public void setSw_lat(double sw_lat) {
        this.sw_lat = sw_lat;
    }

    public double getSw_lng() {
        return sw_lng;
    }

    public void setSw_lng(double sw_lng) {
        this.sw_lng = sw_lng;
    }

    public ViewPort(double ne_lat, double ne_lng, double sw_lat, double sw_lng) {
        this.ne_lat = ne_lat;
        this.ne_lng = ne_lng;
        this.sw_lat = sw_lat;
        this.sw_lng = sw_lng;
    }
}
