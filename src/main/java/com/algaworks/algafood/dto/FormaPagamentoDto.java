package com.algaworks.algafood.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter @Setter
public class FormaPagamentoDto extends RepresentationModel<FormaPagamentoDto> {

	@ApiModelProperty(example = "1")
	private Long Id;
	
	@ApiModelProperty(example = "Dinheiro")
	private String descricao;

}
