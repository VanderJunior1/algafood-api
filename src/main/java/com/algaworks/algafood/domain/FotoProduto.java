package com.algaworks.algafood.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class FotoProduto implements Serializable {
	private static final long serialVersionUID = -4197173113912905339L;

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Long Id;

	@Column(length = 150, nullable = false)
	private String nomeArquivo;
	
	@Column(length = 150, nullable = false)
	private String descricao;
	
	@Column(length = 80, nullable = false)
	private String content_type;
	
	private Long tamanho;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
}
