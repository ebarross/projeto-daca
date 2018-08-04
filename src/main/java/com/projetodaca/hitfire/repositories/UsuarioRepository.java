package com.projetodaca.hitfire.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projetodaca.hitfire.model.Usuario;

// essa inferface sera auto implementada pelo spring numa bean chamada usuarioRepository.

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByNome(String nome);

	public Usuario findByEmail(String email);

}
