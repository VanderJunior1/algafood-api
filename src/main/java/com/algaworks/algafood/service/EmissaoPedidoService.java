package com.algaworks.algafood.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Pedido;

public interface EmissaoPedidoService {

	Pedido buscar(String codigoPedido);

	List<Pedido> findAll();

	Pedido emitir(Pedido pedido);

	public Page<Pedido> findAll(Pageable pageable);
}
