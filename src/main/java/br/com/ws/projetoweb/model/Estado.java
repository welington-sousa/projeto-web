package br.com.ws.projetoweb.model;

import java.util.ArrayList;
import java.util.List;

public class Estado extends BaseModel {
	private String nome;
	private String sigla;

	private List<Cidade> cidades;

	public Estado() {
	}

	public Estado(String nome, String sigla, List<Cidade> cidades) {
		this.nome = nome;
		this.sigla = sigla;
		this.cidades = cidades;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public List<Cidade> getCidades() {
		if (cidades == null) {
			cidades = new ArrayList<Cidade>();
		}
		return cidades;
	}
}
