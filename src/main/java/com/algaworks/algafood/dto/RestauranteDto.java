package com.algaworks.algafood.dto;

import java.math.BigDecimal;

import com.algaworks.algafood.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestauranteDto {

	@JsonView(RestauranteView.Resumo.class)
	private Long Id;
	
	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaDto cozinha;
	
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoDto endereco;
}
