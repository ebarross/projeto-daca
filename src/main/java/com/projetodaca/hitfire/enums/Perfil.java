package com.projetodaca.hitfire.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), USUARIO(2, "ROLE_USUARIO");

	private Integer codigo;
	private String nome;

	private Perfil(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Perfil g : Perfil.values()) {
			if (g.getCodigo() == cod) {
				return g;
			}
		}

		throw new IllegalArgumentException("Código de Perfil inválido: " + cod);
	}

}
