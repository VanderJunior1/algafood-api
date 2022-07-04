package com.algaworks.algafood.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.algaworks.algafood.validator.TaxaFrete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDtoInput {

	@NotBlank
	private String nome;
	
	@NotNull
	@PositiveOrZero
	@TaxaFrete
	private BigDecimal taxaFrete;
	
	@NotNull
	private CozinhaIdInput cozinha;

}
