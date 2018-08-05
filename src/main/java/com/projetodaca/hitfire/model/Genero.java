package com.projetodaca.hitfire.model;

public enum Genero {

	BREGA(1, "Brega"), FORRO(2, "Forró"), FUNK(3, "Funk"), PAGODE(4, "Pagode"), SERTANEJO(5, "Sertanejo");

	private Integer codigo;
	private String nome;

	private Genero(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public static Genero toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Genero g : Genero.values()) {
			if (g.getCodigo() == cod) {
				return g;
			}
		}

		throw new IllegalArgumentException("Código de Genero inválido: " + cod);
	}

}
