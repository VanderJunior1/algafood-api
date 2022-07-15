package com.algaworks.algafood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FotoProdutoDto {

	@ApiModelProperty(example = "ProdutoFoto.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Foto Salada")
	private String descricao;
	
	@ApiModelProperty(example = "image/jpeg")
	private String content_type;
	
	@ApiModelProperty(example = "202912")
	private Long tamanho;

}
