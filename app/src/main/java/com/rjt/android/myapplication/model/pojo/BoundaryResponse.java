package com.rjt.android.myapplication.model.pojo;

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

	static class Response {

		@SerializedName("inputparameter")
		@Expose
		private Inputparameter inputparameter;
		@SerializedName("result")
		@Expose
		private Result result;
		@SerializedName("status")
		@Expose
		private Status status;
		@SerializedName("xmlns")
		@Expose
		private String xmlns;

		public Inputparameter getInputparameter() {
			return inputparameter;
		}

		public void setInputparameter(Inputparameter inputparameter) {
			this.inputparameter = inputparameter;
		}

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public String getXmlns() {
			return xmlns;
		}

		public void setXmlns(String xmlns) {
			this.xmlns = xmlns;
		}
	}
}