package com.projetodaca.hitfire.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class NovoUsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo obrigatório.")
	@Length(min = 10, max = 150, message = "O nome completo deve ter entre 10 e 150 caracteres.")
	private String nome;

	@NotEmpty(message = "Campo obrigatório.")
	@Email(message = "Email inválido.")
	private String email;

	@NotEmpty(message = "Campo obrigatório.")
	private String senha;

	public NovoUsuarioDTO() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
