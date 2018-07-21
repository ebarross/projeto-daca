package com.projetodaca.hitfire.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(path = "add")
	public String addUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuarioRepository.save(usuario);
		return "Salvo!";
	}

	@GetMapping(path = "usuario")
	public Usuario getUsuario(@RequestParam String nome) {
		return usuarioRepository.findByNome(nome);
	}

	@GetMapping
	public Iterable<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

}
