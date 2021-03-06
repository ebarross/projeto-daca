package com.projetodaca.hitfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetodaca.hitfire.model.Usuario;
import com.projetodaca.hitfire.repositories.UsuarioRepository;
import com.projetodaca.hitfire.security.UsuarioSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + email);
		}
		return new UsuarioSS(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}
}
