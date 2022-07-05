package com.algaworks.algafood.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	@Email
	private String email;
}
