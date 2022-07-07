package com.algaworks.algafood.service;

public interface FluxoPedidoService {

	void confirmar(String codigoPedido);
	
	void cancelar(String codigoPedido);
	
	void entregar(String codigoPedido);

}
