package com.JpaConSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JpaConSecurity.model.LoginResponse;
import com.JpaConSecurity.model.LoginUsuario;
import com.JpaConSecurity.model.RegisterUsuario;
import com.JpaConSecurity.model.Usuario;
import com.JpaConSecurity.service.AuthService;
import com.JpaConSecurity.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private JwtService jwtService;
	
	public AuthController(@Autowired AuthService authService,@Autowired JwtService jwtService) {
		this.authService = authService;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> register(@RequestBody RegisterUsuario user){
		Usuario usuario = authService.registrarse(user);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/loggin")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginUsuario user){
		Usuario usuario = authService.loguearse(user);
		
		String token = jwtService.generateToken(usuario);
		
		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setExpiration(jwtService.getExpirationTime());
		return ResponseEntity.ok(response);
	}
}
