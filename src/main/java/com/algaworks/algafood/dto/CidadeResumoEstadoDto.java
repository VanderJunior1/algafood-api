package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CidadeResumoEstadoDto {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Uberlânida")
	private String nome;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String estadoNome;

}
