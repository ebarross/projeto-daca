package com.projetodaca.hitfire.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodaca.hitfire.exception.ResourceNotFoundException;
import com.projetodaca.hitfire.model.Midia;
import com.projetodaca.hitfire.repositories.MidiaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/midias")
@Api("Mídias")
public class MidiaResource {

	@Autowired
	private MidiaRepository repository;

	@ApiOperation("Retorna todas as mídias.")
	@GetMapping
	public ResponseEntity<?> getMidias() {
		List<Midia> midias = (List<Midia>) repository.findAll();

		if (midias.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(midias, HttpStatus.OK);
	}

	@ApiOperation("Retorna uma mídia.")
	@GetMapping("/{id}")
	public ResponseEntity<?> getMidia(@PathVariable Integer id) {
		Optional<Midia> midia = repository.findById(id);

		if (!midia.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		return new ResponseEntity<>(midia, HttpStatus.OK);
	}

}
