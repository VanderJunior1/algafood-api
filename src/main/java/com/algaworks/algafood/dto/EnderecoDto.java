package com.algaworks.algafood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDto {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoEstadoDto cidade;
}
