package com.algaworks.algafood.service;

import java.io.InputStream;

import com.algaworks.algafood.domain.FotoProduto;

public interface CatalogoFotoProdutoService {

	FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo);

	void excluir(Long restauranteId, Long produtoId);

	FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId);

}
