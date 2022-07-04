package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;

@Component
public class RestauranteModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainInput(RestauranteDtoInput restauranteDtoInput) {
		return modelMapper.map(restauranteDtoInput, Restaurante.class);

	}

}
