package com.projetodaca.hitfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodaca.hitfire.model.Plataforma;
import com.projetodaca.hitfire.repositories.PlataformaRepository;

@Service
public class PlataformaService {

	@Autowired
	private PlataformaRepository repository;

	public Plataforma atualizaPlataforma(Plataforma novaPlataforma) {

		Plataforma plataforma = repository.findById(novaPlataforma.getId()).get();

		plataforma.setId(novaPlataforma.getId());
		plataforma.setNome(novaPlataforma.getNome());
		plataforma.setImgLogo(novaPlataforma.getImgLogo());
		plataforma.setSite(novaPlataforma.getSite());

		repository.save(plataforma);

		return plataforma;
	}

}
