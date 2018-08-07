package com.projetodaca.hitfire.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.projetodaca.hitfire.dto.UsuarioDTO;
import com.projetodaca.hitfire.model.Usuario;
import com.projetodaca.hitfire.repositories.UsuarioRepository;
import com.projetodaca.hitfire.security.UsuarioSS;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*
	 * Retorna o usuário logado.
	 */
	public static UsuarioSS authenticated() {
		try {
			return (UsuarioSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		} catch (Exception e) {
			return null;
		}
	}

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
	public Usuario atualizaUsuario(UsuarioDTO novoUsuario) {

		Usuario usuario = usuarioRepository.findById(novoUsuario.getId()).get();

		usuario.setNome(novoUsuario.getNome());
		usuario.setEmail(novoUsuario.getEmail());
		
		usuarioRepository.save(usuario);

		return usuario;
		
	}

	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

}
