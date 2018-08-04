package com.projetodaca.hitfire.model;

public enum GeneroEnum {

	BREGA(1, "Brega"), FORRO(2, "Forró"), FUNK(3, "Funk"), PAGODE(4, "Pagode"), SERTANEJO(5, "Sertanejo");

	private int codigo;
	private String nome;

	private GeneroEnum(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

}
