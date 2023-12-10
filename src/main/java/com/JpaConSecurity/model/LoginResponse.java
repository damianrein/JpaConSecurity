package com.JpaConSecurity.model;

import lombok.Data;

@Data
public class LoginResponse {

	private String token;
	
	private Long expiration;
}
