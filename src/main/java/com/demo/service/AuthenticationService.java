package com.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.dto.AuthenticationResponseDTO;
import com.demo.service.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthenticationService {
	
	@Value("${token.exipration.time.value}")
	private String expirationTimeValue;
	
	@Value("${s3cret.key}")
	private String secretKey;

	public AuthenticationResponseDTO getAuthTokens(Map<String, Object> inputRequest) {
	      String mobileNumber = (String) inputRequest.get("mobile_number");
	      String otp = (String) inputRequest.get("otp");
	      
	      long longTimeValue = Long.valueOf(expirationTimeValue);
	      
	      AuthenticationResponseDTO response = new AuthenticationResponseDTO();

	      byte[] byteArr = secretKey.getBytes();
	      
	      Instant now = Instant.now();
	      
	      String jwt = Jwts.builder()
			      			.claim("mobile_number", mobileNumber)
			      			.claim("otp", otp)
			      			.setIssuedAt(Date.from(now))
			      			.setExpiration(Date.from(now.plus(longTimeValue, ChronoUnit.MINUTES)))
			      			.signWith(Keys.hmacShaKeyFor(byteArr))
			      			.compact();
	      
	      
	      
	      response.setToken(jwt);
	      response.setStatus(Integer.parseInt(Constants.REQUEST));
	      
	      return response;  
	}
	
}
