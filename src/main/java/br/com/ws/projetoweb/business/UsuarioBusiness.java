package br.com.ws.projetoweb.business;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.ws.projetoweb.dao.Repositorio;
import br.com.ws.projetoweb.exception.NenhumRegistroEncontradoException;
import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.Usuario;

@ApplicationScoped
public class UsuarioBusiness {

	private static final Calendar DATA_NASCIMENTO = Calendar.getInstance();
	private Usuario usuario;

	@Inject
	private Repositorio repositorio;

	@PostConstruct
	public void inicializar() {

		DATA_NASCIMENTO.set(1984, 5, 22);
		usuario = new Usuario("215.382.210-63", "Andre Augusto", "andre.augusto@hotmail.com", "andre@augusto",
				DATA_NASCIMENTO.getTime());
		repositorio.insere(usuario);

		// ------------------------------------------------------------------------------------------------------------
		DATA_NASCIMENTO.set(1994, 12, 20);
		usuario = new Usuario("583.764.030-44", "Jessica de Oliveira", "jessica.oliveira@gmail.com", "jessica@oliveira",
				DATA_NASCIMENTO.getTime());
		repositorio.insere(usuario);

		// ------------------------------------------------------------------------------------------------------------
		DATA_NASCIMENTO.set(1971, 6, 2);
		usuario = new Usuario("612.802.790-36", "Fabiana Lira de Mendonça", "fabi.lira@gmail.com", "fabi@lira",
				DATA_NASCIMENTO.getTime());
		repositorio.insere(usuario);

		// ------------------------------------------------------------------------------------------------------------
		DATA_NASCIMENTO.set(1964, 8, 15);
		usuario = new Usuario("612.802.790-36", "Liduina Souza Ferreira", "lidu.ferreira@gmail.com", "lidu@frreira",
				DATA_NASCIMENTO.getTime());
		repositorio.insere(usuario);
	}

	public List<Usuario> selecionar() {
		return repositorio.seleciona(Usuario.class);
	}

	public Usuario selecionar(Long id) throws NenhumRegistroEncontradoException {
		Usuario usuario = repositorio.selecionar(Usuario.class, id);
		if (usuario == null) {
			throw new NenhumRegistroEncontradoException();
		}
		return usuario;
	}

	public Long inserir(Usuario usuario) throws ValidacaoException {
		validar(usuario);
		return repositorio.insere(usuario);
	}

	public void atualizar(Usuario usuario) throws NenhumRegistroEncontradoException, ValidacaoException {
		validar(usuario);
		if (!repositorio.atualiza(usuario)) {
			throw new NenhumRegistroEncontradoException();
		}
	}

	public Usuario excluir(Long id) throws NenhumRegistroEncontradoException {
		Usuario usuario = repositorio.exclui(Usuario.class, id);
		if (usuario == null) {
			throw new NenhumRegistroEncontradoException();
		}
		return usuario;
	}

	private void validar(Usuario usuario) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<Usuario> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}