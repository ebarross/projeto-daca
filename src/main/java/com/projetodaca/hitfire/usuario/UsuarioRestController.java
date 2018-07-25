package com.projetodaca.hitfire.usuario;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.projetodaca.hitfire.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.json.JSONException;
import org.json.JSONObject;

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

		usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping
	public Usuario atualizaUsuario(Usuario usuario) {
		return usuarioService.atualizaUsuario(usuario);
	}

	@ApiOperation(value = "Retorna um usuário")
	@GetMapping("/{id}")
	public Optional<Usuario> getUsuario(@PathVariable Integer id) {
		return usuarioRepository.findById(id);
	}

	@ApiOperation(value = "Exclui um usuário")
	@DeleteMapping("/{id}")
	public void excluiUsuario(@PathVariable Long id) {

	}

	@ApiOperation(value = "Retorna todos os usuários")
	@GetMapping
	public Iterable<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

}
