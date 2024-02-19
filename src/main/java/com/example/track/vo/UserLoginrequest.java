package com.example.track.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginrequest {

	private String userName;
	private String password;
	
}
