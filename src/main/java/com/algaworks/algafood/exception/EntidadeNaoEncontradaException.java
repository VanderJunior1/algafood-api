package com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entidade não encontrada.")
public class EntidadeNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = -1987439854538781878L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
}
