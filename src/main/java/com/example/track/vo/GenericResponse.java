package com.example.track.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class GenericResponse implements Serializable{

	private static final long serialVersionUID = 1l;
	
	private String status;
	private String errorCode;
	private String userDisplayMsg;
	private Object data;
	private String token;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static GenericResponse getSuccessfullresponse(Object obj) throws Exception {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setData(obj);
		genericResponse.setErrorCode("0");
		genericResponse.setStatus("S");
		genericResponse.setUserDisplayMsg("Success");
		
		return genericResponse;	
	}
	
	public static GenericResponse getFailureResponse(String errorCode, String errorMsg) throws Exception {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setData(null);
		genericResponse.setErrorCode(errorCode);
		genericResponse.setStatus("F");
		genericResponse.setUserDisplayMsg(errorMsg);
		
		return genericResponse;	
	}
	
	public static GenericResponse setNoRecordFoundResponse(String status, String errorCode, String errorMsg) throws Exception {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setData(null);
		genericResponse.setErrorCode(errorCode);
		genericResponse.setStatus(status);
		genericResponse.setUserDisplayMsg(errorMsg);
		
		return genericResponse;	
	}
}
