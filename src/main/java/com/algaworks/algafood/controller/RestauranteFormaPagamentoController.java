package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.FormaPagamentoDto;
import com.algaworks.algafood.dto.FormaPagamentoModelAssembler;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@GetMapping
	public List<FormaPagamentoDto> listar(@PathVariable Long restauranteId) {
		log.info("Listando restaurantes");
		Restaurante restaurante = restauranteServiceImpl.buscar(restauranteId);
		
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamentos());
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void dessassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteServiceImpl.dessassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteServiceImpl.associarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
}
