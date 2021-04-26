package com.alby.common;

public class GenericResponseOK extends GenericResponse {
	public GenericResponseOK() {
		this.result = true;
		this.resultCode = ResponseCode.OK.getCode();
		this.resultDescription = ResponseCode.OK.getDescription();
	}
}
