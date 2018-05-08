package com.rjt.android.myapplication.model.pojo_BoundCoordinates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inputparameter {
    @SerializedName("AreaId")
    @Expose
    private String areaId;
    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("service")
    @Expose
    private String service;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
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
}
