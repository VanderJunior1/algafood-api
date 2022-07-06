package com.algaworks.algafood.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -6478452718298606671L;
	private static final String MSG_PERMISSAO_NAO_ENCONTRADA = "Não existe código de cadastro para a Permissão: %d";

	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PermissaoNaoEncontradaException(Long estadoId) {
		this(String.format(MSG_PERMISSAO_NAO_ENCONTRADA, estadoId));
	}
}
