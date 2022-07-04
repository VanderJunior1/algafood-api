package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.core.mixin.CidadeMixin;
import com.algaworks.algafood.core.mixin.CozinhaMixin;
import com.algaworks.algafood.core.mixin.RestauranteMixin;
import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonMixinModule extends SimpleModule{
	private static final long serialVersionUID = 7197240732870772798L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	    setMixInAnnotation(Cidade.class, CidadeMixin.class);
	    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}

}
