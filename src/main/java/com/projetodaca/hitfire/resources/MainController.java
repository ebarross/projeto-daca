package com.projetodaca.hitfire.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class MainController {

	private final String msgHome = "Hitfire API v1.0";

	@GetMapping
	public String home() {
		return msgHome;
	}

}
