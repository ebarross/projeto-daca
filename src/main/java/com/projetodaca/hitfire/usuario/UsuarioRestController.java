package com.projetodaca.hitfire.usuario;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetodaca.hitfire.exception.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Usuários")
class UsuarioRestController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation(value = "Adiciona um usuário")
	@PostMapping
	public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {

		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Usuario novoUsuario = usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoUsuario.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping
	public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario) {
		
		if (usuario == null || usuario.getId() == null) {
			throw new ResourceNotFoundException();
		}
		
		Boolean usuarioExistente = this.usuarioRepository.findById(usuario.getId()).isPresent();

		if (usuarioExistente) {
			this.usuarioService.atualizaUsuario(usuario);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		this.usuarioRepository.save(usuario);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna um usuário")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	@ApiOperation(value = "Exclui um usuário")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluiUsuario(@PathVariable Integer id) {
		
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		
		if (id == null || !usuario.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		usuarioRepository.deleteById(usuario.get().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna todos os usuários")
	@GetMapping
	public ResponseEntity<?> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getUsuarios();

		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

}
