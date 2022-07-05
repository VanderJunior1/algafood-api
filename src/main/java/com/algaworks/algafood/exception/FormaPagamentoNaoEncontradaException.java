package com.algaworks.algafood.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = -5681409640583728852L;

	public FormaPagamentoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }
}
