package com.projetodaca.hitfire.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column(name = "img_perfil")
	private String imgPerfil;

	@Column
	private String email;

	@ManyToOne
	private Genero genero;

	@OneToMany(mappedBy = "artista")
	private List<Midia> midias;

	public Artista(String nome, Genero genero) {
		this.nome = nome;
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgPerfil() {
		return imgPerfil;
	}

	public void setImgPerfil(String imgPerfil) {
		this.imgPerfil = imgPerfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + ", imgPerfil=" + imgPerfil + ", email=" + email + ", genero="
				+ genero + ", midias=" + midias + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imgPerfil == null) ? 0 : imgPerfil.hashCode());
		result = prime * result + ((midias == null) ? 0 : midias.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Artista other = (Artista) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imgPerfil == null) {
			if (other.imgPerfil != null)
				return false;
		} else if (!imgPerfil.equals(other.imgPerfil))
			return false;
		if (midias == null) {
			if (other.midias != null)
				return false;
		} else if (!midias.equals(other.midias))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
