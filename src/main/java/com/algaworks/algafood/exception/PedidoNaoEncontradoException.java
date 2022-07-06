package com.algaworks.algafood.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -1987439854538781878L;
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Não existe um pedido de cadastro com código: %d";

	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public PedidoNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_PEDIDO_NAO_ENCONTRADO, estadoId));
	}
}
