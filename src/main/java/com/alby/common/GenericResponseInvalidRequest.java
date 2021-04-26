package com.alby.common;

public class GenericResponseInvalidRequest extends GenericResponse {
	public GenericResponseInvalidRequest() {
		this.result = false;
		this.resultCode = ResponseCode.INVALID_REQUEST.getCode();
		this.resultDescription = ResponseCode.INVALID_REQUEST.getDescription();
	}

	public GenericResponseInvalidRequest(String additionalInformation) {
		this.result = false;
		this.resultCode = ResponseCode.INVALID_REQUEST.getCode();
		this.resultDescription = ResponseCode.INVALID_REQUEST.getDescription() + ": " + additionalInformation;
	}
}
