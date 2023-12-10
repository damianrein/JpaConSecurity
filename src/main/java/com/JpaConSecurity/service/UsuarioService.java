package com.JpaConSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JpaConSecurity.model.Usuario;
import com.JpaConSecurity.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository userRepo;

	public UsuarioService(IUsuarioRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void createUsuario(Usuario user) {
		userRepo.save(user);
	}
	
	public List<Usuario> allUsuarios(){
		return userRepo.findAll();
	}
}
