package com.example.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.track.entity.UserEntity;
import com.example.track.service.UserLoginService;
import com.example.track.vo.GenericResponse;
import com.example.track.vo.UserLoginrequest;
import com.example.track.vo.UserSignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin
@RestController
@RequestMapping("/api/v2/track")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	/* Login User */
	@PostMapping("/loginuser")
	public ResponseEntity<?> getLoginUser(@RequestBody UserLoginrequest loginRequest) throws Exception{
		GenericResponse response = null;		
	    response = userLoginService.validateUserLoginByUserName(loginRequest);
	    
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	/*SignUp User */
	@PostMapping("/signupuser")
	public ResponseEntity<?> signUpUser(@RequestBody UserSignUpRequest signUpRequest ) throws Exception{
		GenericResponse response = null;
		response = userLoginService.addUser(signUpRequest);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
