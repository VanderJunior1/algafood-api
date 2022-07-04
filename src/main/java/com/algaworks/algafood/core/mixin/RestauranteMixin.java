package com.algaworks.algafood.core.mixin;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Endereco;
import com.algaworks.algafood.domain.FormaPagamento;
import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.validator.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class RestauranteMixin {
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamentos;
	
	@JsonIgnore
	private List<Produto> produtos;

}
