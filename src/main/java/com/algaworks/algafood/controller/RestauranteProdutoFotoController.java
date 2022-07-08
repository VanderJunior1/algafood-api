package com.algaworks.algafood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.domain.FotoProduto;
import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.dto.FotoProdutoDto;
import com.algaworks.algafood.dto.FotoProdutoModelAssembler;
import com.algaworks.algafood.dto.input.FotoProdutoInput;
import com.algaworks.algafood.service.impl.CatalogoFotoProdutoService;
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

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDto atualizarFoto(
			@PathVariable Long restauranteId, 
			@PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {
		log.info("Upload de imagem com nome {}", fotoProdutoInput.getArquivo().getOriginalFilename());

		Produto produto = produtoServiceImpl.buscar(restauranteId, produtoId);
		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto fotoProduto = new FotoProduto();
		fotoProduto.setProduto(produto);
		fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
		fotoProduto.setTamanho(arquivo.getSize());
		fotoProduto.setContent_type(arquivo.getContentType());
		fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto);
		return fotoProdutoModelAssembler.toModel(fotoSalva);

	}

}
