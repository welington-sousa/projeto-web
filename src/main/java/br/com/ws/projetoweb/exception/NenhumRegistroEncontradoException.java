package br.com.ws.projetoweb.exception;

public class NenhumRegistroEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public NenhumRegistroEncontradoException() {
		super("Nenhum registro encontrado");
	}
}
