package br.com.ws.projetoweb.business;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.ws.projetoweb.dao.Repositorio;
import br.com.ws.projetoweb.exception.NenhumRegistroEncontradoException;
import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.Veiculo;
import br.com.ws.projetoweb.validator.ModelValidator;

@ApplicationScoped
public class VeiculoBusiness {

	private Veiculo veiculo;

	@Inject
	private Repositorio repositorio;

	@PostConstruct
	public void inicializar() {
		veiculo = new Veiculo("JDO-7454", "Andre Augusto", Calendar.getInstance().getTime(),
				NumberFormat.getCurrencyInstance().format(234.20));
		repositorio.insere(veiculo);

		veiculo = new Veiculo("NAX-6128", "Jessica de Oliveira", Calendar.getInstance().getTime(),
				NumberFormat.getCurrencyInstance().format(179.30));
		repositorio.insere(veiculo);

		veiculo = new Veiculo("NCB-6630", "Fabiana Lira de Mendonça", Calendar.getInstance().getTime(),
				NumberFormat.getCurrencyInstance().format(169.60));
		repositorio.insere(veiculo);

		veiculo = new Veiculo("LYI-3318", "Liduina Souza Ferreira", Calendar.getInstance().getTime(),
				NumberFormat.getCurrencyInstance().format(152.34));
		repositorio.insere(veiculo);
	}

	public List<Veiculo> selecionaTodos() {
		return repositorio.seleciona(Veiculo.class);
	}

	public Veiculo selecionaPorId(Long id) throws NenhumRegistroEncontradoException {
		Veiculo veiculo = repositorio.selecionar(Veiculo.class, id);
		registroEncontrado(veiculo);
		return veiculo;
	}

	private void registroEncontrado(Veiculo veiculo) throws NenhumRegistroEncontradoException {
		if (veiculo.equals(null))
			throw new NenhumRegistroEncontradoException();
	}

	public Long adicionaVeiculo(Veiculo veiculo) throws ValidacaoException {
		ModelValidator.validar(veiculo);
		return repositorio.insere(veiculo);
	}

	public void atualizaVeiculo(Veiculo veiculo) throws NenhumRegistroEncontradoException, ValidacaoException {
		ModelValidator.validar(veiculo);
		if (!repositorio.atualiza(veiculo))
			throw new NenhumRegistroEncontradoException();
	}

	public Veiculo excluiVeiculo(Long id) throws NenhumRegistroEncontradoException {
		Veiculo veiculo = repositorio.exclui(Veiculo.class, id);
		registroEncontrado(veiculo);
		return veiculo;
	}
}
