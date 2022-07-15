package com.algaworks.algafood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.FluxoPedidoControllerOpenApi;
import com.algaworks.algafood.service.impl.FluxoPedidoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi{

	@Autowired
	private FluxoPedidoServiceImpl fluxoPedidoServiceImpl;

	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String codigoPedido) {
		log.info("Confirmando pedido Código:  {}", codigoPedido);

		fluxoPedidoServiceImpl.confirmar(codigoPedido);
	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String codigoPedido) {
		log.info("Cancelando pedido Código:  {}", codigoPedido);

		fluxoPedidoServiceImpl.cancelar(codigoPedido);
	}

	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable String codigoPedido) {
		log.info("Entregando pedido Código:  {}", codigoPedido);

		fluxoPedidoServiceImpl.entregar(codigoPedido);
	}
}
