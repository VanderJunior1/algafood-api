package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {
	
	@ApiModelProperty(example = "Dinheiro")
	@NotBlank
	private String descricao;

}
