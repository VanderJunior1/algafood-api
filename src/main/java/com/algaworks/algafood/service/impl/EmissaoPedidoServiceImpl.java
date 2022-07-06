package com.algaworks.algafood.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Pedido;
import com.algaworks.algafood.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.repository.PedidoRepository;
import com.algaworks.algafood.service.EmissaoPedidoService;

@Service
public class EmissaoPedidoServiceImpl implements EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido buscar(Long id) {
		return pedidoRepository	.findById(id).orElseThrow(
				() -> new PedidoNaoEncontradoException(id));
	}

}
