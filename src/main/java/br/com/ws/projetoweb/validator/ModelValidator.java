package br.com.ws.projetoweb.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.BaseModel;

public class ModelValidator {

	public static <T extends BaseModel> void validar(T model) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(model);

		if (!violations.isEmpty()) {

			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<T> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}
