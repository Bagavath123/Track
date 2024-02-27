package com.example.track.jwtSecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;



@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private final JwtService jwtService;
  @Autowired
  private final UserDetailsService userDetailsService;
 

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException, java.io.IOException {
    if (request.getServletPath().contains("/api/v2/track/loginuser")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
	
	//to do extract the username from JWT token
    username = jwtService.extractUsername(jwt);
	if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		
	//token is valid
		if(jwtService.isTokenvalid(jwt, userDetails)) {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
					(userDetails,null,userDetails.getAuthorities()) ;
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
		//Update Security context holder
		SecurityContextHolder.getContext().setAuthentication(authToken);
		   }
		filterChain.doFilter(request, response);
	}
		
  }
}

