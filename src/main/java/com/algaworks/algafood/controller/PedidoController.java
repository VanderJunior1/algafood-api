package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Pedido;
import com.algaworks.algafood.dto.PedidoDto;
import com.algaworks.algafood.dto.PedidoModelAssembler;
import com.algaworks.algafood.dto.PedidoResumoDto;
import com.algaworks.algafood.dto.PedidoResumoModelAssembler;
import com.algaworks.algafood.service.impl.EmissaoPedidoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private EmissaoPedidoServiceImpl emissaoPedidoServiceImpl;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@GetMapping
	public List<PedidoResumoDto> listar() {
		log.info("Buscando lista de pedidos");

		List<Pedido> todosPedidos = emissaoPedidoServiceImpl.findAll();
		return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
	}

	@GetMapping("/{pedidoId}")
	public PedidoDto buscar(@PathVariable Long pedidoId) {
		log.info("Buscando lista de pedidos de id {}", pedidoId);

		Pedido pedido = emissaoPedidoServiceImpl.buscar(pedidoId);
		return pedidoModelAssembler.toModel(pedido);
	}

}
