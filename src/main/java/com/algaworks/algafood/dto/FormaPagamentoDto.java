package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDto {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Dinheiro")
	private String descricao;

}
