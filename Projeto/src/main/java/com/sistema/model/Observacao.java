package com.sistema.model;

public enum Observacao {
	ORCAMENTO("Visita para orçamento"),
	ENTREGA("Entrega e instalação"),
	MANUTENCAO("Manutenção de equipamentos");

	private String descricao;
	
	Observacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
