package com.algaworks.algafood.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 641560203578844705L;
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe código de cadastro para a cidade: %d";

	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
	}
}
