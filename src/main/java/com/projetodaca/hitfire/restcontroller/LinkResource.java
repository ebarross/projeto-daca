package com.projetodaca.hitfire.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodaca.hitfire.repositories.LinkRepository;

@RestController
@RequestMapping("/links")
public class LinkResource {

	@Autowired
	private LinkRepository repository;

}
