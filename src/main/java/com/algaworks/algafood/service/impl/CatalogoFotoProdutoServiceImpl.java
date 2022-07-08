package com.algaworks.algafood.service.impl;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.FotoProduto;
import com.algaworks.algafood.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.repository.ProdutoRepository;
import com.algaworks.algafood.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoServiceImpl implements CatalogoFotoProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageServiceImpl fotoStorageServiceImpl;

	@Override
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorageServiceImpl.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null; 
				
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
		fotoStorageServiceImpl.substituir(novaFoto, nomeArquivoExistente);
		return foto;
		
	}
	
	@Override
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
	    FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
	    
	    produtoRepository.delete(foto);
	    produtoRepository.flush();

	    fotoStorageServiceImpl.remover(foto.getNomeArquivo());
	}
	
	@Override
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
	    return produtoRepository.findFotoById(restauranteId, produtoId)
	            .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	} 
}
