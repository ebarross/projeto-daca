package com.projetodaca.hitfire.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// essa inferface sera auto implementada pelo spring numa bean chamada usuarioRepository.

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	public Usuario findByNome(@Param("nome") String nome);

	public Usuario findByEmail(String email);

}
