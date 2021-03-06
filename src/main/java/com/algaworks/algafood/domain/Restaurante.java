package com.algaworks.algafood.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.validator.Groups;
import com.algaworks.algafood.validator.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Entity
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Restaurante implements Serializable {
	private static final long serialVersionUID = -2144929629430526856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long Id;

	@NotBlank
	@Column(length = 100, nullable = false)
	private String nome;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;
	
	//@DecimalMin("0")
	@NotNull
	@PositiveOrZero
	@TaxaFrete
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, updatable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(insertable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@Valid
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@OneToMany
	(mappedBy ="restaurante" )
	private List<Produto> produtos = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"), 
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamentos = new HashSet<>();
	
	public void ativar(){
		setAtivo(true);
	}

	public void inativar(){
		setAtivo(false);
	}
	
	public void abrir(){
		setAberto(true);
	}

	public void fechar(){
		setAberto(false);
	}
	
	public boolean dessassociarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamentos().remove(formaPagamento);
	}
	
	public boolean associarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamentos().add(formaPagamento);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamentos().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}
}
