package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter @Setter
public class CidadeDto extends RepresentationModel<CidadeDto>{

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	
	private EstadoDto estado;

}
