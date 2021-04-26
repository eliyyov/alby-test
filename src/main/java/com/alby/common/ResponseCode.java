package com.alby.common;

public enum ResponseCode {
	OK("OK", "OK"),
	
	INVALID_REQUEST("INVALID_REQUEST", "INVALID REQUEST"),
	
	INVALID_ENCRYPTION("INVALID_ENCRYPTION", "INVALID ENCRYPTION"),
	
	GENERAL_ERROR("ERROR", "ERROR"),
	
	DATA_NOT_FOUND("DATA_NOT_FOUND", "DATA NOT FOUND"),
	
	DATA_ALREADY_EXIST("DATA_ALREADY_EXIST", "DATA ALREADY EXIST"),;

	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private ResponseCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

}

