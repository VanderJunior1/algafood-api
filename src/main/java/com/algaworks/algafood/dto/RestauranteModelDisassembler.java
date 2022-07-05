package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;

@Component
public class RestauranteModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainInput(RestauranteDtoInput restauranteDtoInput) {
		return modelMapper.map(restauranteDtoInput, Restaurante.class);

	}

	public void copyToDomainObject(RestauranteDtoInput restauranteDtoInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());

		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		modelMapper.map(restauranteDtoInput, restaurante);
	}
}
