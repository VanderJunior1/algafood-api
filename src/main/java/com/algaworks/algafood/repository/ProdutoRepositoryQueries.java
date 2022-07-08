package com.algaworks.algafood.repository;

import com.algaworks.algafood.domain.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save (FotoProduto fotoProduto);
	
	void delete (FotoProduto fotoProduto);
}
