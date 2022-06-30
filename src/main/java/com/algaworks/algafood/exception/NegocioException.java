package com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 488520182039125270L;
	
	public NegocioException(String msg) {
		super(msg);
	}
}
