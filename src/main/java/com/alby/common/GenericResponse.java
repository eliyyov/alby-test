package com.alby.common;

public class GenericResponse {

	protected boolean result = false;
	protected String resultCode = "";
	protected String resultDescription = "";

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public GenericResponse(boolean result, String resultCode, String resultDescription) {
		super();
		this.result = result;
		this.resultCode = resultCode;
		this.resultDescription = resultDescription;
	}

	public GenericResponse() {
		super();
	}
}

