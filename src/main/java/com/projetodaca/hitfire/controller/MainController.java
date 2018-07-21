package com.projetodaca.hitfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodaca.hitfire.usuario.UsuarioRepository;

@RestController
@RequestMapping("/")
class MainController {

	private final String msgHome = "Hitfire API v1.0";

	@RequestMapping("/")
	public String home() {
		return msgHome;
	}

}
