package com.rjt.android.myapplication.model.pojo_AreaId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item{
	@SerializedName("area")
	@Expose
	private String area;
	@SerializedName("area_unit")
	@Expose
	private String areaUnit;
	@SerializedName("geo_key")
	@Expose
	private String geoKey;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("type")
	@Expose
	private String type;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaUnit() {
		return areaUnit;
	}

	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}

	public String getGeoKey() {
		return geoKey;
	}

	public void setGeoKey(String geoKey) {
		this.geoKey = geoKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
