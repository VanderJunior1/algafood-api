package com.algaworks.algafood.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Restaurante;

@Component
public class RestauranteModelAssembler {
	
	public RestauranteDto toModel(Restaurante restaurante) {
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
	
	public List<RestauranteDto> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}

}
