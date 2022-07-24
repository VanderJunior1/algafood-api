package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.RestauranteFormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.FormaPagamentoDto;
import com.algaworks.algafood.dto.FormaPagamentoModelAssembler;
import com.algaworks.algafood.security.CheckSecurity;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public List<FormaPagamentoDto> listar(@PathVariable Long restauranteId) {
		log.info("Listando restaurantes");
		Restaurante restaurante = restauranteServiceImpl.buscar(restauranteId);
		
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamentos());
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void dessassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteServiceImpl.dessassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteServiceImpl.associarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
}

