package com.projetodaca.hitfire.usuario;

import org.springframework.data.repository.CrudRepository;

// essa inferface sera auto implementada pelo spring numa bean chamada usuarioRepository.

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByNome(String nome);

	public Usuario findByEmail(String email);

}
