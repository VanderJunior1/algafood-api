package com.algaworks.algafood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.service.impl.FluxoPedidoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/pedidos/{pedidoId}")
public class FluxoPedidoController {

	@Autowired
	private FluxoPedidoServiceImpl fluxoPedidoServiceImpl;

	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long pedidoId) {
		log.info("Confirmando pedido de Id {}", pedidoId);

		fluxoPedidoServiceImpl.confirmar(pedidoId);
	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long pedidoId) {
		log.info("Cancelando pedido de Id {}", pedidoId);

		fluxoPedidoServiceImpl.cancelar(pedidoId);
	}

	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable Long pedidoId) {
		log.info("Entregando pedido de Id {}", pedidoId);

		fluxoPedidoServiceImpl.entregar(pedidoId);
	}
}
