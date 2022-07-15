package com.algaworks.algafood.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoInput {
	
	@ApiModelProperty(example = "Sanduíche X-Tudo", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Sanduíche completo", required = true)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "20.00", required = true)
	@NotNull
    @PositiveOrZero
    private BigDecimal preco;
    
	@ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean ativo;
}
