package com.projetodaca.hitfire.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetodaca.hitfire.exception.ResourceNotFoundException;
import com.projetodaca.hitfire.model.Artista;
import com.projetodaca.hitfire.model.Midia;
import com.projetodaca.hitfire.repositories.ArtistaRepository;
import com.projetodaca.hitfire.repositories.MidiaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/artistas")
@Api("Artistas")
public class ArtistaResource {

	@Autowired
	private ArtistaRepository repository;

	@Autowired
	private MidiaRepository midiaRepository;

	@ApiOperation("Retorna todos os artistas.")
	@GetMapping
	public ResponseEntity<?> getArtistas() {
		List<Artista> artistas = (List<Artista>) repository.findAll();

		if (artistas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(artistas, HttpStatus.OK);
	}

	@ApiOperation("Retorna um artista.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getArtista(@PathVariable Integer id) {
		Optional<Artista> artista = repository.findById(id);

		if (!artista.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		return new ResponseEntity<>(artista.get(), HttpStatus.OK);
	}

	@ApiOperation("Adiciona um artista.")
	@PostMapping
	public ResponseEntity<?> addArtista(@RequestBody Artista artista) {

		if (artista == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Artista novoArtista = repository.save(artista);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoArtista.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation("Atualiza um artista.")
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaArtista(@PathVariable Integer id, @RequestBody Artista artista) {
		return null;
	}

	@ApiOperation("Exclui um artista.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluiArtista() {
		return null;
	}

	@ApiOperation("Retorna todas as mídias de um artista.")
	@GetMapping(value = "/{id}/midias")
	public ResponseEntity<?> getMidias(@PathVariable Integer id) {

		Optional<Artista> artista = repository.findById(id);

		if (!artista.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		List<Midia> midias = artista.get().getMidias();

		if (midias.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(midias, HttpStatus.OK);
	}

	@ApiOperation("Adiciona uma mídia a um artista.")
	@PostMapping(value = "/{id}/midias")
	public ResponseEntity<?> addMidia(@PathVariable Integer id, @RequestBody Midia midia) {
		Optional<Artista> artista = repository.findById(id);

		if (!artista.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		midia.setArtista(artista.get());
		Midia novaMidia = midiaRepository.save(midia);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaMidia.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation("Retorna uma mídia especifica de um artista.")
	@GetMapping(value = "/{idArtista}/midias/{idMidia}")
	public ResponseEntity<?> getMidia(@PathVariable Integer idArtista, @PathVariable Integer idMidia) {

		if (idArtista == null || idMidia == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<Artista> artista = repository.findById(idArtista);

		if (!artista.isPresent()) {
			throw new ResourceNotFoundException(idArtista.toString());
		}

		List<Midia> midias = artista.get().getMidias();

		for (Midia m : midias) {
			if (m.getId() == idMidia) {
				return new ResponseEntity<>(m, HttpStatus.OK);
			}
		}

		throw new ResourceNotFoundException(idMidia.toString());
	}

}
