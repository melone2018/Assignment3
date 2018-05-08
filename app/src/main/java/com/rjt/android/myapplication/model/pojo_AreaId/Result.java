package com.rjt.android.myapplication.model.pojo_AreaId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result{

		@SerializedName("package")
		@Expose
		private Package _package;
		@SerializedName("xml_record")
		@Expose
		private String xmlRecord;

		public Package getPackage() {
			return _package;
		}

		public void setPackage(Package _package) {
			this._package = _package;
		}

		public String getXmlRecord() {
			return xmlRecord;
		}

		public void setXmlRecord(String xmlRecord) {
			this.xmlRecord = xmlRecord;
		}
}
