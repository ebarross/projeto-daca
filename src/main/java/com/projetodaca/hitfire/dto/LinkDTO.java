package com.projetodaca.hitfire.dto;

import java.io.Serializable;

import com.projetodaca.hitfire.model.Link;

public class LinkDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String url;

	public LinkDTO() {
	}
	
	public LinkDTO(Link link) {
		this.id = link.getId();
		this.url = link.getUrl();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
