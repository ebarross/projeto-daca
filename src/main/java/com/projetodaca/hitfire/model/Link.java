package com.projetodaca.hitfire.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "midia_id")
	private Midia midia;

	@ManyToOne
	@JoinColumn(name = "plataforma_id")
	private Plataforma plataforma;

	@Column(nullable = false)
	private String url;

	public Link() {
	}

	public Link(Midia midia, Plataforma plataforma, String url) {
		this.midia = midia;
		this.plataforma = plataforma;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", midia=" + midia + ", plataforma=" + plataforma + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		result = prime * result + ((plataforma == null) ? 0 : plataforma.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (midia == null) {
			if (other.midia != null)
				return false;
		} else if (!midia.equals(other.midia))
			return false;
		if (plataforma == null) {
			if (other.plataforma != null)
				return false;
		} else if (!plataforma.equals(other.plataforma))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
