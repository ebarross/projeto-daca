package com.projetodaca.hitfire.repository;

import org.springframework.data.repository.CrudRepository;
import com.projetodaca.hitfire.model.Usuario;

// essa inferface sera auto implementada pelo spring numa bean chamada usuarioRepository.

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
