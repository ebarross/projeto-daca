package com.projetodaca.hitfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetodaca.hitfire.model.Usuario;
import com.projetodaca.hitfire.repository.UsuarioRepository;


@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("/")
	public String home() {
		return "Olá";
	}
	
	@GetMapping(path="/add")
	public String addUsuario(@RequestParam String nome, @RequestParam String email) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuarioRepository.save(usuario);
		return "Salvo!";
	}

	@GetMapping(path="usuarios")
	public Iterable<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}
}
