package com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends ResponseStatusException {
	private static final long serialVersionUID = 488520182039125270L;

	public EntidadeEmUsoException(HttpStatus status, String msg) {
		super(status, msg);
	}

	public EntidadeEmUsoException(String msg) {
		super(HttpStatus.CONFLICT, msg);
	}
}
