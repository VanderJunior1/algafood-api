package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter @Setter
public class UsuarioDto extends RepresentationModel<UsuarioDto>{

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Jose Silva")
	private String nome;
	
	@ApiModelProperty(example = "jose@email.com")
	private String email;

}
