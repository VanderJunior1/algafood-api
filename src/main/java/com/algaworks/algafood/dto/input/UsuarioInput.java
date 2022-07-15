package com.algaworks.algafood.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "Jose Silva", required = true)
	@NotBlank
	private String nome;

	
	@ApiModelProperty(example = "email", required = true)
	@NotBlank
	@Email
	private String email;
}
