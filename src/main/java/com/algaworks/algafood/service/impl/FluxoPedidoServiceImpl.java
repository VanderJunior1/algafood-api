package com.algaworks.algafood.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Pedido;
import com.algaworks.algafood.service.FluxoPedidoService;

@Service
public class FluxoPedidoServiceImpl implements FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoServiceImpl emissaoPedidoServiceImpl;

	@Override
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoServiceImpl.buscar(codigoPedido);
		pedido.confirmar();
	}
	
	@Override
	@Transactional
	public void cancelar(String codigoPedido) {
	    Pedido pedido = emissaoPedidoServiceImpl.buscar(codigoPedido);
	    pedido.cancelar();
	}

	@Override
	@Transactional
	public void entregar(String codigoPedido) {
	    Pedido pedido = emissaoPedidoServiceImpl.buscar(codigoPedido);
	    pedido.entregar();
	}
}
