package com.algaworks.algafood.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Sanduíche X-Tudo")
	private String nome;
	
	@ApiModelProperty(example = "Sanduíche completo")
	private String descicao;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
}
