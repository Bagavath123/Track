package com.example.track.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignUpRequest {

	private String userName;
	private String password;
	private int status;
	private String transactionId;
	private int roleId;
	
		
	
}
