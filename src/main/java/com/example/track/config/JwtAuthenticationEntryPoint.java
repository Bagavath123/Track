package com.example.track.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	    @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response,
	                          AuthenticationException authException) throws IOException, ServletException {
	        // Handle unauthorized requests (usually send a 401 response)
	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        response.setContentType("application/json");
	        response.getWriter().write("{\"message\": \"Unauthorized access\"}");
	    }
}

