package com.rjt.android.myapplication;

import java.util.ArrayList;

public class MyLocation {
    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    private String formatted_address;
    private ViewPort vpoint;

    public GeometryLoc getGmLoc() {
        return gmLoc;
    }

    public void setGmLoc(GeometryLoc gmLoc) {
        this.gmLoc = gmLoc;
    }

    private GeometryLoc gmLoc;

    public String getIcon() {
        return icon;
    }

    String icon, id, name, place_id, reference;
    ArrayList<String> types;

    public MyLocation(String formatted_address, ViewPort vpoint, GeometryLoc gmLoc, String icon, String id, String name, String place_id, String reference, ArrayList<String> types) {
        this.formatted_address = formatted_address;
        this.vpoint = vpoint;
        this.gmLoc = gmLoc;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.place_id = place_id;
        this.reference = reference;
        this.types = types;
    }
}
