package com.example.track.jwtSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  
  /*Extract the username form Token */
  public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	//generate token
	public String generateToken (UserDetails userDetails) {		
		return generateToken(new HashMap<>(), userDetails);	
	}
	//Generate token from UserDetails and Expriration time
	public String generateToken(
			Map<String, Object> extractClaims,UserDetails userDetails) {

		    return buildToken(extractClaims, userDetails, jwtExpiration);
		  }
	//This method helps to build the structure for the token
	private String buildToken(
	          Map<String, Object> extraClaims,
	          UserDetails userDetails,
	          long expiration
	  ) {
	    return Jwts
	            .builder()
	            .setClaims(extraClaims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration))
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	  }
	
	//validate the token
	public boolean isTokenvalid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);	
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
	}

	private Key getSignInKey() {
		byte[]keyBytes = Decoders.BASE64.decode(secretKey);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
