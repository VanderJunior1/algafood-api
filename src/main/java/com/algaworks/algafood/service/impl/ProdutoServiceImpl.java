package com.algaworks.algafood.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.repository.ProdutoRepository;
import com.algaworks.algafood.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	@Transactional
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto buscar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId).orElseThrow(
				()-> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}

	@Override
	public List<Produto> findByRestaurante(Restaurante restaurante) {
		return produtoRepository.findByRestaurante(restaurante);
	}

}
