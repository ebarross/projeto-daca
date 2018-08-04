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
import com.projetodaca.hitfire.model.Plataforma;
import com.projetodaca.hitfire.repositories.PlataformaRepository;
import com.projetodaca.hitfire.services.PlataformaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plataformas")
@Api(value = "Plataformas")
public class PlataformaResource {

	@Autowired
	private PlataformaRepository plataformaRepository;

	@Autowired
	private PlataformaService service;

	@ApiOperation(value = "Retorna todas as plataformas.")
	@GetMapping
	public ResponseEntity<?> getPlataformas() {
		List<Plataforma> plataformas = (List<Plataforma>) plataformaRepository.findAll();

		if (plataformas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(plataformas, HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma plataforma de acordo com id passado como parametro.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPlataforma(@PathVariable Integer id) {
		Optional<Plataforma> plataforma = plataformaRepository.findById(id);

		if (!plataforma.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		return new ResponseEntity<>(plataforma, HttpStatus.OK);
	}

	@ApiOperation(value = "Adiciona uma plataforma.")
	@PostMapping
	public ResponseEntity<?> addPlataforma(@RequestBody Plataforma plataforma) {

		if (plataforma == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Plataforma novaPlataforma = plataformaRepository.save(plataforma);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novaPlataforma.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Atualiza uma plataforma.")
	@PutMapping
	public ResponseEntity<?> atualizaPlataforma(@RequestBody Plataforma plataforma) {

		if (plataforma == null || plataforma.getId() == null) {
			throw new ResourceNotFoundException();
		}

		Boolean plataformaExistente = this.plataformaRepository.findById(plataforma.getId()).isPresent();

		if (plataformaExistente) {
			this.service.atualizaPlataforma(plataforma);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		plataformaRepository.save(plataforma);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@ApiOperation(value = "Exclui uma plataforma.")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletaPlataforma(@PathVariable Integer id) {

		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<Plataforma> plataforma = this.plataformaRepository.findById(id);

		if (!plataforma.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		this.plataformaRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
