package br.com.ws.projetoweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.ws.projetoweb.adapter.DateAdapter;

public class Veiculo extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String placa;
	private String nomeProprietario;

	@Past
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dataEmplacamento;
	private String valorIPVA;

	public Veiculo(String placa, String nomeProprietario, Date dataEmplacamento, String valorIPVA) {
		this.placa = placa;
		this.nomeProprietario = nomeProprietario;
		this.dataEmplacamento = dataEmplacamento;
		this.valorIPVA = valorIPVA;
	}

	public Veiculo() {
	}

	public String getPlaca() {
		return placa;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public Date getDataEmplacamento() {
		return dataEmplacamento;
	}

	public String getValorIPVA() {
		return valorIPVA;
	}
}
