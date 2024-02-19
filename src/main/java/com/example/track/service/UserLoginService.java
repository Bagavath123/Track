package com.example.track.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.track.entity.UserEntity;
import com.example.track.repository.UserRepository;
import com.example.track.vo.GenericResponse;
import com.example.track.vo.UserLoginrequest;
import com.example.track.vo.UserSignUpRequest;

@Service
public class UserLoginService {

	@Autowired
	private UserRepository userRepository;
	
	
	/* SignUp User */
	public GenericResponse addUser(UserSignUpRequest signUpRequest) throws Exception {
		
		GenericResponse response = new GenericResponse();
		try {
			//check if the user already exists
		 	UserEntity existingUser = userRepository.findByUsername(signUpRequest.getUserName());
			if(existingUser != null) {
				return GenericResponse.getFailureResponse("108", "User Already Exists");
			}
			
			// Create a new user entity
			UserEntity newUser = new UserEntity();
			newUser.setUsername(signUpRequest.getUserName());
			newUser.setPassword(signUpRequest.getPassword());
//			String encodedPassword = this.passwordEncoder.encode(signUpRequest.getPassword());
//			newUser.setPassword(encodedPassword);
			newUser.setStatus(1);
			newUser.setTransactionId(signUpRequest.getTransactionId());
			newUser.setRole(signUpRequest.getRole());
			
			userRepository.save(newUser);
			
			response.setData(newUser);
			response.setErrorCode("0");
	        response.setStatus("S");
	        response.setUserDisplayMsg("User signed up successfully");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return GenericResponse.getFailureResponse("108","Exception in signUpUser: " + e.getMessage());
		}
		return response;
		    
	}
	
	/* Login by Username and Password */
	public GenericResponse validateUserLoginByUserName(UserLoginrequest loginrequest) throws Exception{
		GenericResponse response = null;
		
		UserEntity userLogin = new UserEntity();
           try {
			userLogin = userRepository.loginUser(loginrequest.getUserName(), loginrequest.getPassword());

			if (userLogin != null && userLogin.getStatus() != 0) {

				response = new GenericResponse();
				response.setData(userLogin);
				response.setErrorCode("0");
				response.setStatus("S");
				response.setUserDisplayMsg("Login Successfully");
			} else {
				return GenericResponse.getFailureResponse("108", "Invalid username or password");
			}

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return GenericResponse.getFailureResponse("108", "Exception in validateUserLogin " + ex.getMessage());
		}

		return response;
	}
}
