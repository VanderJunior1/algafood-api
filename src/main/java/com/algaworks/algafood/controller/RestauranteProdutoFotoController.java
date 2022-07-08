package com.algaworks.algafood.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.domain.FotoProduto;
import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.dto.FotoProdutoDto;
import com.algaworks.algafood.dto.FotoProdutoModelAssembler;
import com.algaworks.algafood.dto.input.FotoProdutoInput;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.service.impl.CatalogoFotoProdutoService;
import com.algaworks.algafood.service.impl.FotoStorageServiceImpl;
import com.algaworks.algafood.service.impl.ProdutoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;

	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private FotoStorageServiceImpl fotoStorageServiceImpl;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDto atualizarFoto(
			@PathVariable Long restauranteId, 
			@PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		log.info("Upload de imagem com nome {}", fotoProdutoInput.getArquivo().getOriginalFilename());

		Produto produto = produtoServiceImpl.buscar(restauranteId, produtoId);
		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto fotoProduto = new FotoProduto();
		fotoProduto.setProduto(produto);
		fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
		fotoProduto.setTamanho(arquivo.getSize());
		fotoProduto.setContent_type(arquivo.getContentType());
		fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto, arquivo.getInputStream());
		return fotoProdutoModelAssembler.toModel(fotoSalva);

	}

	@GetMapping
	public FotoProdutoDto buscar(@PathVariable Long restauranteId, 
	        @PathVariable Long produtoId) {
	    FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
	    
	    return fotoProdutoModelAssembler.toModel(fotoProduto);
	}
	
	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> recuperarFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContent_type());
			List<MediaType> mediatypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediatypesAceitas);
			
			InputStream inputStream = fotoStorageServiceImpl.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(
			MediaType mediaTypeFoto, List<MediaType> mediatypesAceitas) throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediatypesAceitas.stream()
				.anyMatch(mediatypesAceita -> mediatypesAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediatypesAceitas);
		}
	}
	
}
