package com.rjt.android.myapplication.model.pojo_AreaId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inputparameter{
	@SerializedName("latitude")
	@Expose
	private String latitude;
	@SerializedName("longitude")
	@Expose
	private String longitude;
	@SerializedName("package")
	@Expose
	private String _package;
	@SerializedName("resource")
	@Expose
	private String resource;
	@SerializedName("service")
	@Expose
	private String service;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
