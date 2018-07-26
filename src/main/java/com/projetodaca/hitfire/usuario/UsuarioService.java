package com.projetodaca.hitfire.usuario;

import java.util.List;
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

	/*
	 * apenas atualizando, não verifica se objeto existe.
	 */
	public Usuario atualizaUsuario(Usuario novoUsuario) {

		Usuario usuario = usuarioRepository.findById(novoUsuario.getId()).get();

		usuario.setNome(novoUsuario.getNome());
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setSenha(novoUsuario.getSenha());
		
		usuarioRepository.save(usuario);

		return usuario;
		
	}

	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

}
