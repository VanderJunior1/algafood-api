package com.algaworks.algafood.exception;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1695465034372854566L;

	public NegocioException(String msg) {
		super(msg);
	}
	
	public NegocioException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
