package com.rjt.android.myapplication.model.pojo_BoundCoordinates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordResponse {
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
