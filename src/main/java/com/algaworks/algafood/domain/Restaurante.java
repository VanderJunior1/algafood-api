package com.algaworks.algafood.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.EqualsAndHashCode;

public class Restaurante implements Serializable {
	private static final long serialVersionUID = -2144929629430526856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long Id;

	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

}
