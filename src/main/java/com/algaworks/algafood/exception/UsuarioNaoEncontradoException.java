package com.algaworks.algafood.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 5780149982116719939L;
	private static final String MSG_USUARIO_NAO_ENCONTRADO = "Não existe código de cadastro para o usuário: %d";

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format(MSG_USUARIO_NAO_ENCONTRADO, usuarioId));
	}
}
