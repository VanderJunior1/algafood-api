package com.algaworks.algafood.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {
	
	@NotNull
	private Long Id;
}
