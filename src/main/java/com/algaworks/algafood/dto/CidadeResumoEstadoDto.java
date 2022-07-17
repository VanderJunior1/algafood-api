package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter @Setter
public class CidadeResumoEstadoDto extends RepresentationModel<CidadeResumoEstadoDto> {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Uberlânida")
	private String nome;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String estadoNome;

}
