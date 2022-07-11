package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDto {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "EDITAR_COZINHAS")
	private String nome;
	
	@ApiModelProperty(example = "Permite editar cozinhas")
	private String descricao;

}
