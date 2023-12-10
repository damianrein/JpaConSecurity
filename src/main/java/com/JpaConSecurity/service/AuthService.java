package com.JpaConSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JpaConSecurity.model.LoginUsuario;
import com.JpaConSecurity.model.RegisterUsuario;
import com.JpaConSecurity.model.Usuario;
import com.JpaConSecurity.repository.IUsuarioRepository;

@Service
public class AuthService {

	@Autowired
	private IUsuarioRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthService(@Autowired IUsuarioRepository userRepo,@Autowired PasswordEncoder passwordEncoder,
			@Autowired AuthenticationManager authenticationManager) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	
	public Usuario registrarse(RegisterUsuario user) {
		Usuario usuario = new Usuario();
		usuario.setName(user.getName());
		usuario.setEmail(user.getEmail());
		usuario.setClave(passwordEncoder.encode(user.getClave()));
		return userRepo.save(usuario);
	}
	
	public Usuario loguearse(LoginUsuario user) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getClave()));
		
		return userRepo.findByEmail(user.getEmail()).orElseThrow();
	}
}
