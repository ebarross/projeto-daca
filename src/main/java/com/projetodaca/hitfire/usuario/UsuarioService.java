package com.projetodaca.hitfire.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario getUsuario(Integer id) {
		return null;
	}

	public Usuario addUsuario(Usuario usuario) {
		Usuario usuarioNovo = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
		return usuarioRepository.save(usuarioNovo);
	}

	public Usuario atualizaUsuario(Usuario novoUsuario) {

		Usuario usuario = usuarioRepository.findById(novoUsuario.getId()).get();

		usuario.setNome(novoUsuario.getNome());
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setSenha(novoUsuario.getSenha());

		return usuario;
	}

}
