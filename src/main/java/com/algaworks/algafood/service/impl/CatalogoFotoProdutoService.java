package com.algaworks.algafood.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.FotoProduto;
import com.algaworks.algafood.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto) {
		Long restauranteId = fotoProduto.getRestauranteId();
		Long produtoId = fotoProduto.getProduto().getId();
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}
		
		return produtoRepository.save(fotoProduto);
	}
}
