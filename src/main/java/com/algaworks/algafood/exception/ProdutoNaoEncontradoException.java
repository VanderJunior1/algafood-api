package com.algaworks.algafood.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -8728069102515521066L;
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Não existe um cadastro de produto com código %d para o restaurante de código %d";

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId));
    }
    
}
