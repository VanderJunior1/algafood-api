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
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedidoServiceImpl.buscar(pedidoId);
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(Long pedidoId) {
	    Pedido pedido = emissaoPedidoServiceImpl.buscar(pedidoId);
	    pedido.cancelar();
	}

	@Transactional
	public void entregar(Long pedidoId) {
	    Pedido pedido = emissaoPedidoServiceImpl.buscar(pedidoId);
	    pedido.entregar();
	}
}
