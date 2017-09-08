package br.com.ws.projetoweb.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.ws.projetoweb.dao.Repositorio;
import br.com.ws.projetoweb.exception.NenhumRegistroEncontradoException;
import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.Cidade;
import br.com.ws.projetoweb.model.Estado;
import br.com.ws.projetoweb.validator.ModelValidator;

@ApplicationScoped
public class EstadoBusiness {

	@Inject
	private Repositorio repositorio;

	@PostConstruct
	public void inicializar() {
		Estado ceara = new Estado("Ceará", "CE", new ArrayList<Cidade>());
		repositorio.insere(ceara);

		Cidade fortaleza = new Cidade("Fortaleza", "13/04/1726", BigDecimal.valueOf(8.778576), "4,776");
		repositorio.insere(fortaleza);

		ceara.getCidades().add(fortaleza);

		// ---------------------------------------------------------------------------
		Estado alagoas = new Estado("Alagoas", "AL", new ArrayList<Cidade>());
		repositorio.insere(alagoas);

		Cidade maceio = new Cidade("Maceió", "30/09/1892", BigDecimal.valueOf(3.300935), "5,275");
		repositorio.insere(maceio);

		alagoas.getCidades().add(maceio);

		// ---------------------------------------------------------------------------
		Estado bahia = new Estado("Bahia", "BA", new ArrayList<Cidade>());
		repositorio.insere(bahia);

		Cidade salvador = new Cidade("Salvador", "29/03/1549", BigDecimal.valueOf(15.044137), "6,418");
		repositorio.insere(salvador);

		bahia.getCidades().add(salvador);

		// ---------------------------------------------------------------------------
		Estado maranhao = new Estado("Maranhão", "MA", new ArrayList<Cidade>());
		repositorio.insere(maranhao);

		Cidade saoluis = new Cidade("São Luís", "08/09/1612", BigDecimal.valueOf(6.794301), "4,213");
		repositorio.insere(saoluis);

		maranhao.getCidades().add(saoluis);
	}

	public List<Estado> selecionarTodos() {
		return repositorio.seleciona(Estado.class);
	}

	public Estado selecionarPorId(Long id) throws NenhumRegistroEncontradoException {
		Estado estado = repositorio.selecionar(Estado.class, id);

		if (estado == null) {
			throw new NenhumRegistroEncontradoException();
		}

		return estado;
	}

	public Long adicionarEstado(Estado estado) throws ValidacaoException {
		ModelValidator.validar(estado);
		return repositorio.insere(estado);
	}

	public void atualizarEstado(Estado estado) throws NenhumRegistroEncontradoException, ValidacaoException {
		ModelValidator.validar(estado);
		if (!repositorio.atualiza(estado)) {
			throw new NenhumRegistroEncontradoException();
		}
	}

	public Estado excluirEstado(Long id) throws NenhumRegistroEncontradoException {
		Estado estado = repositorio.exclui(Estado.class, id);
		if (estado == null) {
			throw new NenhumRegistroEncontradoException();
		}
		return estado;
	}
}
