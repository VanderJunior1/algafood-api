package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {
	
	@ApiModelProperty(example = "Japonesa")
	@NotBlank
	private String nome;
}
