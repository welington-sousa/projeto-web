package br.com.ws.projetoweb.model;

import java.math.BigDecimal;

public class Cidade extends BaseModel {
	private String nome;
	private String dataConstituicao;
	private BigDecimal populacao;
	private String pib;

	public Cidade() {
	}

	public Cidade(String nome, String dataConstituicao, BigDecimal populacao, String pib) {
		this.nome = nome;
		this.dataConstituicao = dataConstituicao;
		this.populacao = populacao;
		this.pib = pib;
	}

	public String getNome() {
		return nome;
	}

	public String getDataConstituicao() {
		return dataConstituicao;
	}

	public BigDecimal getPopulacao() {
		return populacao;
	}

	public String getPib() {
		return pib;
	}
}
