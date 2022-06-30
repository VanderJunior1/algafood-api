package com.algaworks.algafood.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 7396003643592597877L;
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código %d não pode ser removida";

	public RestauranteNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId));
	}
}
