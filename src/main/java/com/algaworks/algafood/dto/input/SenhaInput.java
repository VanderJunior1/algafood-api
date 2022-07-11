package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SenhaInput {
	
	@ApiModelProperty(example = "senhaAtual")
	@NotBlank
    private String senhaAtual;
    
	@ApiModelProperty(example = "novaSenha")
    @NotBlank
    private String novaSenha;
}
