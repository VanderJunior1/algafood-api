package com.algaworks.algafood.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDto {

	private Long Id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaDto cozinha;
	private Boolean ativo;
}
