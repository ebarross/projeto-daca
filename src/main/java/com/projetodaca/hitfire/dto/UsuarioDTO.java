package com.projetodaca.hitfire.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.projetodaca.hitfire.model.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo obrigatório.")
	@Length(min = 10, max = 150, message = "O nome completo deve ter entre 10 e 150 caracteres.")
	private String nome;

	@NotEmpty(message = "Campo obrigatório.")
	@Email(message = "Email inválido.")
	private String email;

	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
