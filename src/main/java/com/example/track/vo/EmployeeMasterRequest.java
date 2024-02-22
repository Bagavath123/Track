package com.example.track.vo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeMasterRequest {

	private String empCode;
	
	private String employeeName;
	
	private String password;
	
	private String departmentName;
	
	private String projectName;
	
	//private String systemMacId;
	

	
	//private String employeeRole;
	//private String mobileNumber;	
	//private String employeeEmail;	
	//private Date empDoj;
	//private int districtId;	
	//private String reportingTo;

	
	
	
}
