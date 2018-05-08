package com.rjt.android.myapplication.model.pojo_BoundCoordinates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Package {
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("item")
    @Expose
    private List<Item> item = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("notice")
    @Expose
    private String notice;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("version")
    @Expose
    private String version;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
