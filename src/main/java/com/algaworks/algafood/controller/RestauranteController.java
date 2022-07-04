package com.algaworks.algafood.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.CozinhaDto;
import com.algaworks.algafood.dto.RestauranteDto;
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

	@GetMapping
	public List<RestauranteDto> listar() {
		log.info("Listando restaurantes");
		return toCollectionModel(restauranteServiceImpl.findAll());
	}

	@GetMapping("/{id}")
	public RestauranteDto findById(@PathVariable Long id) {
		log.info("Buscando restaurante do id {}", id);
		Restaurante restaurante = restauranteServiceImpl.buscar(id);
		return toModel(restaurante);		
	}

	@PutMapping("/{id}")
	public RestauranteDto atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		log.info("Atualizando restaurante do id {}", id);
		Restaurante restaurante = toDomainInput(restauranteDtoInput);
		Restaurante restauranteAtual = restauranteServiceImpl.buscar(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", "formasPagamentos", "endereco", "dataCadastro", "produtos");
		try {
			return toModel(restauranteServiceImpl.save(restauranteAtual));
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
			Restaurante restaurante = toDomainInput(restauranteDtoInput);
			return toModel(restauranteServiceImpl.save(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	private RestauranteDto toModel(Restaurante restaurante) {
		CozinhaDto cozinhaDto = new CozinhaDto();
		cozinhaDto.setId(restaurante.getCozinha().getId());
		cozinhaDto.setNome(restaurante.getCozinha().getNome());

		RestauranteDto restauranteDto = new RestauranteDto();
		restauranteDto.setId(restaurante.getId());
		restauranteDto.setNome(restaurante.getNome());
		restauranteDto.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteDto.setCozinhaDto(cozinhaDto);
		return restauranteDto;
	}
	
	private List<RestauranteDto> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
	
	private Restaurante toDomainInput(RestauranteDtoInput restauranteDtoInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteDtoInput.getNome());
		restaurante.setTaxaFrete(restauranteDtoInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteDtoInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante;
		
	}
}
