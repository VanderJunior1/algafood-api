package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CidadeDto {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	
	private CidadeResumoEstadoDto estado;

}
