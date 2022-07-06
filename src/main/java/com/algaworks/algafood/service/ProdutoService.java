package com.algaworks.algafood.service;

import java.util.List;

import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.domain.Restaurante;

public interface ProdutoService {

	public Produto save(Produto produto);

	List<Produto> findByRestaurante(Restaurante restaurante);
	
	public Produto buscar(Long restauranteId, Long produtoId);
}
