package com.example.demo.entity;

public class UserResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserResponse(String token) {
		super();
		this.token = token;
	}

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
