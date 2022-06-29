package com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entidade não encontrada.")
public class EntidadeNaoEncontradaoException extends ResponseStatusException {
	private static final long serialVersionUID = -1987439854538781878L;

	public EntidadeNaoEncontradaoException(HttpStatus status, String mensagem) {
		super(status, mensagem);
		// TODO Auto-generated constructor stub
	}

	public EntidadeNaoEncontradaoException(String msg) {
		super(HttpStatus.NOT_FOUND, msg);
	}
}
