package com.example.track.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.track.entity.UserEntity;
import com.example.track.jwtSecurity.JwtService;
import com.example.track.repository.UserRepository;
import com.example.track.vo.GenericResponse;
import com.example.track.vo.UserLoginrequest;
import com.example.track.vo.UserSignUpRequest;

@Service
public class UserLoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
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
			//newUser.setPassword(signUpRequest.getPassword());
			newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
			newUser.setStatus(1);
			newUser.setTransactionId(signUpRequest.getTransactionId());
			newUser.setRoleId(1);
			
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
		try {
		    // Authenticate the user
		    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginrequest.getUserName(), loginrequest.getPassword()));

		    // If authentication successful, retrieve user details from repository
		    UserEntity userLogin = userRepository.findByUsername(loginrequest.getUserName());

		    if (userLogin != null && userLogin.getStatus() != 0) {
		        // Generate JWT token
		        String jwtToken = jwtService.generateToken(userLogin);

		        // Prepare response
		        GenericResponse response = new GenericResponse();
		        response.setData(userLogin);
		        response.setErrorCode("0");
		        response.setStatus("S");
		        response.setUserDisplayMsg("Login Successfully");
		        response.setToken(jwtToken);

		        // Return the response
		        return response;
		    } else {
		        // Handle invalid user or status
		    	return GenericResponse.getFailureResponse("108", "Invalid username or password");
		    }
		} catch (Exception ex) {
			ex.printStackTrace();
			return GenericResponse.getFailureResponse("108", "Exception in validateUserLogin " + ex.getMessage());
		}
   }
}
	
	
