package com.projetodaca.hitfire.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projetodaca.hitfire.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Integer> {
	public Artista findByNome(String nome);
}
