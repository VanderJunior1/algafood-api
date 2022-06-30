package com.algaworks.algafood.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -1987439854538781878L;
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe código de cadastro para o estado: %d";

	public EstadoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId));
	}
}
