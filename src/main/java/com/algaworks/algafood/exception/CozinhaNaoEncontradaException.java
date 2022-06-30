package com.algaworks.algafood.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 4691428040411421103L;
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe código de cadastro para a cozinha: %d";

	public CozinhaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
	}
}
