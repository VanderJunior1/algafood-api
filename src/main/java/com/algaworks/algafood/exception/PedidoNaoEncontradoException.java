package com.algaworks.algafood.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -1987439854538781878L;
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Não existe um pedido de cadastro com código: %d";

	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format(MSG_PEDIDO_NAO_ENCONTRADO, codigoPedido));
	}
}
