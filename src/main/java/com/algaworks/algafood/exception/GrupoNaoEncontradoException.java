package com.algaworks.algafood.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -1987439854538781878L;
	private static final String MSG_GRUPO_NAO_ENCONTRADO = "Não existe código de cadastro para o Grupo: %d";

	public GrupoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public GrupoNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_GRUPO_NAO_ENCONTRADO, estadoId));
	}
}
