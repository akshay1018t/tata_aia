package com.demo.dto;

public class AuthenticationResponseDTO {

	private int status;
	private String token;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
