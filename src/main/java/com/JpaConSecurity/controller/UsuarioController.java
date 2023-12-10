package com.JpaConSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JpaConSecurity.model.Usuario;
import com.JpaConSecurity.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController(@Autowired UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAllUsuarios(){
		List<Usuario> user = usuarioService.allUsuarios();
		return ResponseEntity.ok(user);
	}
}
