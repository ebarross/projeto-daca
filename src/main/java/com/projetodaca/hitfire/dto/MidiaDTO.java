package com.projetodaca.hitfire.dto;

import java.io.Serializable;

import com.projetodaca.hitfire.model.Midia;

public class MidiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer artista_id;

	public MidiaDTO() {
	}

	public MidiaDTO(Midia midia) {
		this.id = midia.getId();
		this.nome = midia.getNome();
		this.artista_id = midia.getArtista().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getArtista_id() {
		return artista_id;
	}

	public void setArtista_id(Integer artista_id) {
		this.artista_id = artista_id;
	}

}
