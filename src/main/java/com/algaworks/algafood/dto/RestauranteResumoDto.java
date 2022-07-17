package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter @Setter
public class RestauranteResumoDto extends RepresentationModel<RestauranteResumoDto> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
}
