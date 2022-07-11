package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInput {
	
	@ApiModelProperty(example = "1")
	@NotNull
	private Long Id;
}
