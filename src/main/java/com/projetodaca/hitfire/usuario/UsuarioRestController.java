package com.projetodaca.hitfire.usuario;

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

import com.projetodaca.hitfire.ResourceNotFoundException;

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
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping
	public Usuario atualizaUsuario(Usuario usuario) {
		return usuarioService.atualizaUsuario(usuario);
	}

	@ApiOperation(value = "Retorna um usuário")
	@GetMapping("/{id}")
	public Optional<Usuario> getUsuario(@PathVariable Integer id) {
		return usuarioRepository
				.findById(id);
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
