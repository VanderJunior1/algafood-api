package com.algaworks.algafood.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.controller.openapi.RestauranteProdutoFotoControllerOpenApi;
import com.algaworks.algafood.domain.FotoProduto;
import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.dto.FotoProdutoDto;
import com.algaworks.algafood.dto.FotoProdutoModelAssembler;
import com.algaworks.algafood.dto.input.FotoProdutoInput;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.security.CheckSecurity;
import com.algaworks.algafood.service.FotoStorageService.FotoRecuperada;
import com.algaworks.algafood.service.impl.CatalogoFotoProdutoServiceImpl;
import com.algaworks.algafood.service.impl.ProdutoServiceImpl;
import com.algaworks.algafood.service.impl.S3FotoStorageServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

	@Autowired
	private CatalogoFotoProdutoServiceImpl catalogoFotoProdutoService;

	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private S3FotoStorageServiceImpl fotoStorageServiceImpl;

	@CheckSecurity.Restaurantes.PodeEditar
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDto atualizarFoto(
			@PathVariable Long restauranteId, 
			@PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		log.info("Upload de imagem com nome {}", fotoProdutoInput.getArquivo().getOriginalFilename());

		Produto produto = produtoServiceImpl.buscar(restauranteId, produtoId);
//		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto fotoProduto = new FotoProduto();
		fotoProduto.setProduto(produto);
		fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
		fotoProduto.setTamanho(arquivo.getSize());
		fotoProduto.setContent_type(arquivo.getContentType());
		fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto, arquivo.getInputStream());
		return fotoProdutoModelAssembler.toModel(fotoSalva);

	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public FotoProdutoDto buscar(@PathVariable Long restauranteId, 
	        @PathVariable Long produtoId) {
	    FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
	    
	    return fotoProdutoModelAssembler.toModel(fotoProduto);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@GetMapping(produces = MediaType.ALL_VALUE	)
	public ResponseEntity<?> recuperarFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContent_type());
			List<MediaType> mediatypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediatypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorageServiceImpl.recuperar(fotoProduto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();				
			} else {
				return ResponseEntity.ok()
						.contentType(MediaType.IMAGE_JPEG)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restauranteId, 
	        @PathVariable Long produtoId) {
		catalogoFotoProdutoService.excluir(restauranteId, produtoId);
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
