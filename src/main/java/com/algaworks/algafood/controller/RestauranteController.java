package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.RestauranteDto;
import com.algaworks.algafood.dto.RestauranteModelAssembler;
import com.algaworks.algafood.dto.RestauranteModelDisassembler;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteModelDisassembler restauranteModelDisassembler;

	@GetMapping
	public List<RestauranteDto> listar() {
		log.info("Listando restaurantes");
		return restauranteModelAssembler.toCollectionModel(restauranteServiceImpl.findAll());
	}

	@GetMapping("/{id}")
	public RestauranteDto findById(@PathVariable Long id) {
		log.info("Buscando restaurante do id {}", id);
		Restaurante restaurante = restauranteServiceImpl.buscar(id);
		return restauranteModelAssembler.toModel(restaurante);		
	}

	@PutMapping("/{id}")
	public RestauranteDto atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		log.info("Atualizando restaurante do id {}", id);
		Restaurante restaurante = restauranteModelDisassembler.toDomainInput(restauranteDtoInput);
		Restaurante restauranteAtual = restauranteServiceImpl.buscar(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", "formasPagamentos", "endereco", "dataCadastro", "produtos");
		try {
			return restauranteModelAssembler.toModel(restauranteServiceImpl.save(restauranteAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo restaurante do id {}", id);
		restauranteServiceImpl.deleteById(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public RestauranteDto adicionar(@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		log.info("Cadastrando novo restaurante de nome {}", restauranteDtoInput.getNome());
		try {
			Restaurante restaurante = restauranteModelDisassembler.toDomainInput(restauranteDtoInput);
			return restauranteModelAssembler.toModel(restauranteServiceImpl.save(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
