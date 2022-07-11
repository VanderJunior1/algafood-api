package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {
	
	@ApiModelProperty(example = "1")
	@NotBlank
	private String nome;
}
