package com.projetodaca.hitfire.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projetodaca.hitfire.model.Plataforma;

public interface PlataformaRepository extends CrudRepository<Plataforma, Integer> {

	public Plataforma findByNome(String nome);

}
