package com.algaworks.algafood.dto;

import com.algaworks.algafood.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDto {

	@JsonView(RestauranteView.Resumo.class)
	private Long Id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
