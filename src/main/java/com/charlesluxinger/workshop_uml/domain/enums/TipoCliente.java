package com.charlesluxinger.workshop_uml.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int id;
	private String descricao;

	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static TipoCliente toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (TipoCliente tipo : TipoCliente.values()) {
			if (id.equals(tipo.getId())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
