package com.example.track.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_employee")
public class EmployeeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "code")
	@NotNull
	private String empCode;
	
	@Column(name = "name" , length = 25)
	@NotNull
	private String employeeName;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "created_ts" ,columnDefinition = "TIMESTAMP")
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdTime;
	
	@Column(name = "password", length = 25)
	@NotNull
	private String password;
	
	@Column(name = "role_id" , length = 2)
	private String employeeRole = "R1";
	
	@Column(name = "mac_id")
	private String systemMacId;
	 
	 
	 @Column(name = "department")
	 private String departmentName;
	    

	 @Column(name = "project")
	 private String projectName;
	 
	 @PrePersist
	 protected void onCreate() {
	     createdTime = LocalDateTime.now();
	 }

	
	/*
	 * @Column(name = "mobile_no" , length = 10)
	 * 
	 * @NotNull private String mobileNumber;
	 */
	/*
	 * @Column(name = "email" ,length = 30)
	 * 
	 * @NotNull private String employeeEmail;
	 */
	
	
	/*
	 * @Column(name = "doj")//date of joining private Date empDoj;
	 * 
	 * @Column(name = "district_id") private int districtId;
	 * 
	 * @Column(name = "reporting_to") private String reportingTo;
	 */
	

	
}
