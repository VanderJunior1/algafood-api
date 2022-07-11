package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPedidoInput {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "1")
	@NotNull
    @PositiveOrZero
    private Integer quantidade;
    
	@ApiModelProperty(example = "Ao ponto")
    private String observacao;
}
