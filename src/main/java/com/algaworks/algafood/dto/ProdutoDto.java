package com.algaworks.algafood.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

	private Long Id;
	private String nome;
	private String descicao;
	private BigDecimal preco;
	private Boolean ativo;
}
