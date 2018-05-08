package com.rjt.android.myapplication.model.pojo_AreaId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoundaryResponse{
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