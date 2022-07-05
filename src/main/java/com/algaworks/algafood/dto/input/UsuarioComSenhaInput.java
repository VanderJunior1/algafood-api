package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioComSenhaInput {
	
    @NotBlank
    private String senha;
}
