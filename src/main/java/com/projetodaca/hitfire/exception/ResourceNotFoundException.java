package com.projetodaca.hitfire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8104997979997705990L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String id) {
		super("Não foi possível encontrar o recurso de id: " + id);
	}

}
