package com.algaworks.algafood.core.mixin;

import java.util.List;

import com.algaworks.algafood.domain.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CozinhaMixin {
	
	@JsonIgnore
	List<Restaurante> restaurantes;

}
