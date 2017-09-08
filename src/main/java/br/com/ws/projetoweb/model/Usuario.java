package br.com.ws.projetoweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.ws.projetoweb.adapter.DateAdapter;

public class Usuario extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;

	private String cpf;
	private String nome;
	private String email;
	private String senha;

	@Past
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dataNascimento;

	public Usuario() {
	}

	public Usuario(String cpf, String nome, String email, String senha, Date dataNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public Usuario(Long id) {
		this();
		setId(id);
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Date getData() {
		return dataNascimento;
	}
}