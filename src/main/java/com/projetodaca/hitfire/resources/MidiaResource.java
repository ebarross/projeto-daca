package com.projetodaca.hitfire.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetodaca.hitfire.dto.LinkDTO;
import com.projetodaca.hitfire.dto.MidiaDTO;
import com.projetodaca.hitfire.exception.ResourceNotFoundException;
import com.projetodaca.hitfire.model.Link;
import com.projetodaca.hitfire.model.Midia;
import com.projetodaca.hitfire.model.Plataforma;
import com.projetodaca.hitfire.repositories.LinkRepository;
import com.projetodaca.hitfire.repositories.MidiaRepository;
import com.projetodaca.hitfire.repositories.PlataformaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/midias")
@Api("Mídias")
public class MidiaResource {

	@Autowired
	private MidiaRepository repository;

	@Autowired
	private PlataformaRepository plataformaRepository;

	@Autowired
	private LinkRepository linkRepository;

	@ApiOperation("Retorna todas as mídias.")
	@GetMapping
	public ResponseEntity<?> getMidias() {
		List<Midia> midias = (List<Midia>) repository.findAll();

		if (midias.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<MidiaDTO> midiasDto = midias.stream().map(obj -> new MidiaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(midiasDto, HttpStatus.OK);
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

	@ApiOperation("Retorna todos os links de uma mídia.")
	@GetMapping("/{id}/links")
	public ResponseEntity<?> getLinks(@PathVariable Integer id) {
		Optional<Midia> midia = repository.findById(id);

		if (!midia.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		List<Link> links = midia.get().getLinks();

		if (links.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(links, HttpStatus.OK);
	}
	
	//@ApiOperation("Retorna todos os links dado uma mídia e uma plataforma.")

	@ApiOperation("Adiciona um link a uma mídia e uma plataforma.")
	@PostMapping("/{idMidia}/plataformas/{idPlataforma}/links")
	public ResponseEntity<?> addLink(@PathVariable Integer idMidia, @PathVariable Integer idPlataforma,
			@RequestBody LinkDTO linkDto) {
		Optional<Midia> midia = repository.findById(idMidia);
		Optional<Plataforma> plataforma = plataformaRepository.findById(idPlataforma);

		if (!midia.isPresent() || !plataforma.isPresent()) {
			throw new ResourceNotFoundException();
		}

		Link link = new Link(midia.get(), plataforma.get(), linkDto.getUrl());

		Link novoLink = linkRepository.save(link);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoLink.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
