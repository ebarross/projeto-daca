package com.projetodaca.hitfire.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario addUsuario(Usuario usuario) {
		Usuario usuarioNovo = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
		return usuarioRepository.save(usuarioNovo);
	}

	public Usuario atualizaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
