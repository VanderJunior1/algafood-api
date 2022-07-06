package com.algaworks.algafood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.ProdutoDto;
import com.algaworks.algafood.dto.ProdutoInputDisassembler;
import com.algaworks.algafood.dto.ProdutoModelAssembler;
import com.algaworks.algafood.dto.input.ProdutoInput;
import com.algaworks.algafood.service.impl.ProdutoServiceImpl;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@GetMapping("/{id}")
	public ProdutoDto findById(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		log.info("Buscando produto de id {}", produtoId);
		Produto produto = produtoServiceImpl.buscar(restauranteId, produtoId);
		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoDto adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		log.info("Cadastrando novo produto de nome {}", produtoInput.getNome());
		Restaurante restaurante = restauranteServiceImpl.buscar(restauranteId);

		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		produto = produtoServiceImpl.save(produto);
		return produtoModelAssembler.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoDto atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = produtoServiceImpl.buscar(restauranteId, produtoId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		produtoAtual = produtoServiceImpl.save(produtoAtual);
		return produtoModelAssembler.toModel(produtoAtual);
	}

}
