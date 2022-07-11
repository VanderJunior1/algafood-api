package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FotoProdutoDto {

	@ApiModelProperty(example = "Nome Foto")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Foto Salada")
	private String descricao;
	private String content_type;
	private Long tamanho;

}
