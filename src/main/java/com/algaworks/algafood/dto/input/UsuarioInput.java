package com.algaworks.algafood.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "Jose Silva")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "senha")
	@NotBlank
	private String senha;
	
	@ApiModelProperty(example = "email")
	@NotBlank
	@Email
	private String email;
}
