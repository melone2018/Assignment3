package com.rjt.android.myapplication.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status{

	@SerializedName("code")
	@Expose
	private String code;
	@SerializedName("long_description")
	@Expose
	private String longDescription;
	@SerializedName("short_description")
	@Expose
	private String shortDescription;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}
