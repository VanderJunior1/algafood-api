package com.algaworks.algafood.dto;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;

@Component
public class RestauranteModelDisassembler {
	
	public Restaurante toDomainInput(RestauranteDtoInput restauranteDtoInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteDtoInput.getNome());
		restaurante.setTaxaFrete(restauranteDtoInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteDtoInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante;
		
	}

}
