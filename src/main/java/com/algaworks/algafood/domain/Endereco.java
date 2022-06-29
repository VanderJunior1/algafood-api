package com.algaworks.algafood.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = -1591062996079781568L;


	@Column(name = "endereco_cep", length = 9, nullable = false)
	private String cep;
	
	@Column(name = "endereco_logradouro", length = 20, nullable = false)
	private String logradouro;
	
	@Column(name = "endereco_numero", length = 10, nullable = false)
	private String numero;
	
	@Column(name = "endereco_complemento", length = 20)
	private String complemento;
	
	@Column(name = "endereco_bairro", length = 20, nullable = false)
	private String bairro;

	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id", nullable = false)
	private Cidade cidade;

}
