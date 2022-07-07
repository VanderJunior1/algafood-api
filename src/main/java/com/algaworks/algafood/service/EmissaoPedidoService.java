package com.algaworks.algafood.service;

import java.util.List;

import com.algaworks.algafood.domain.Pedido;

public interface EmissaoPedidoService {

	Pedido buscar(String codigoPedido);

	List<Pedido> findAll();

	Pedido emitir(Pedido pedido);

}
