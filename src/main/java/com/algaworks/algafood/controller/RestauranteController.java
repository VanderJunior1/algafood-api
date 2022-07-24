package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.RestauranteControllerOpenApi;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.RestauranteDto;
import com.algaworks.algafood.dto.RestauranteModelAssembler;
import com.algaworks.algafood.dto.RestauranteModelDisassembler;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;
import com.algaworks.algafood.dto.view.RestauranteView;
import com.algaworks.algafood.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.security.CheckSecurity;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteModelDisassembler restauranteModelDisassembler;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
		log.info("Listando restaurantes");
		List<Restaurante> restaurantes = restauranteServiceImpl.findAll();
		List<RestauranteDto> restauranteDtos = restauranteModelAssembler.toCollectionModel(restaurantes);
		
		MappingJacksonValue restaurenteMapper = new MappingJacksonValue(restauranteDtos);
		restaurenteMapper.setSerializationView(RestauranteView.Resumo.class);
		
		if ("apenas-nome".equals(projecao)) {
			restaurenteMapper.setSerializationView(RestauranteView.ApenasNome.class);
		} else if ("completo".equals(projecao)) {
			restaurenteMapper.setSerializationView(null);
		}
		return restaurenteMapper;
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{id}")
	public RestauranteDto findById(@PathVariable Long id) {
		log.info("Buscando restaurante do id {}", id);
		Restaurante restaurante = restauranteServiceImpl.buscar(id);
		return restauranteModelAssembler.toModel(restaurante);		
	}

	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/{id}")
	public RestauranteDto atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		log.info("Atualizando restaurante do id {}", id);
		Restaurante restauranteAtual = restauranteServiceImpl.buscar(id);
		restauranteModelDisassembler.copyToDomainObject(restauranteDtoInput, restauranteAtual);
		
		try {
			return restauranteModelAssembler.toModel(restauranteServiceImpl.save(restauranteAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public RestauranteDto adicionar(@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		log.info("Cadastrando novo restaurante de nome {}", restauranteDtoInput.getNome());
		try {
			Restaurante restaurante = restauranteModelDisassembler.toDomainInput(restauranteDtoInput);
			return restauranteModelAssembler.toModel(restauranteServiceImpl.save(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/{id}/ativo")
	public void ativar(@PathVariable Long id) {
		restauranteServiceImpl.ativar(id);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}/inativo")
	public void inativar(@PathVariable Long id) {
		restauranteServiceImpl.inativar(id);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/ativacoes")
	public void inativar(@RequestBody List<Long> restauranteIds) {
		restauranteServiceImpl.ativar(restauranteIds);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/desativacoes")
	public void desativarMultiplos(@RequestBody List<Long> restauranteIds) {
		restauranteServiceImpl.inativar(restauranteIds);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/{id}/abertura")
	public void abrir(@PathVariable Long id) {
		restauranteServiceImpl.abrir(id);
	}

	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/{id}/fechamento")
	public void fechar(@PathVariable Long id) {
		restauranteServiceImpl.fechar(id);
	}
	
}

