package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter @Setter
public class EstadoDto extends RepresentationModel<EstadoDto> {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;

}
