package com.algaworks.algafood.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoInput {
	
	@ApiModelProperty(example = "38400-000")
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Av. Central")
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "1010")
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Apt 303")
	private String complemento;
	
	@ApiModelProperty(example = "Centro")
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
