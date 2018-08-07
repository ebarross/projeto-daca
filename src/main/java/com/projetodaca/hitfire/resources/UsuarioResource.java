package com.projetodaca.hitfire.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetodaca.hitfire.dto.NovoUsuarioDTO;
import com.projetodaca.hitfire.dto.UsuarioDTO;
import com.projetodaca.hitfire.exception.ResourceNotFoundException;
import com.projetodaca.hitfire.model.Usuario;
import com.projetodaca.hitfire.repositories.UsuarioRepository;
import com.projetodaca.hitfire.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Usuários")
class UsuarioResource {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation(value = "Adiciona um usuário")
	@PostMapping
	public ResponseEntity<?> addUsuario(@RequestBody NovoUsuarioDTO usuarioDto) {

		if (usuarioDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario(usuarioDto.getNome(), usuarioDto.getEmail(), pe.encode(usuarioDto.getSenha()));

		Usuario novoUsuario = usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoUsuario.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDto) {

		if (usuarioDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Boolean usuarioExistente = this.usuarioRepository.findById(id).isPresent();
		
		if (!usuarioExistente) {
			throw new ResourceNotFoundException(id.toString());
		}

		this.usuarioService.atualizaUsuario(usuarioDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um usuário")
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Integer id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
			throw new ResourceNotFoundException(id.toString());
		}

		UsuarioDTO usuarioDto = new UsuarioDTO(usuario.get());
		return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
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

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Retorna todos os usuários")
	@GetMapping
	public ResponseEntity<?> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getUsuarios();

		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<UsuarioDTO> usuariosDto = usuarios.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());

		return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
	}

}
