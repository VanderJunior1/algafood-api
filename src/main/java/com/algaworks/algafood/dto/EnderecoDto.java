package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDto {

	@ApiModelProperty(example = "38400-000")
	private String cep;
	
	@ApiModelProperty(example = "Av. Central")
	private String logradouro;
	
	@ApiModelProperty(example = "1010")
	private String numero;
	
	@ApiModelProperty(example = "Apt 303")
	private String complemento;
	
	@ApiModelProperty(example = "Centro")
	private String bairro;
	private CidadeResumoEstadoDto cidade;
}
