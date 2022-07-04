package com.algaworks.algafood.core.mixin;

import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.validator.Groups;

public abstract class CidadeMixin {
	
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	private Estado estado;
	
	

}
