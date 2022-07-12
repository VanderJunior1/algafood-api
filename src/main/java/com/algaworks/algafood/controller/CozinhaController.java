package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.dto.CozinhaDto;
import com.algaworks.algafood.dto.CozinhaInputDisassembler;
import com.algaworks.algafood.dto.CozinhaModelAssembler;
import com.algaworks.algafood.dto.input.CozinhaInput;
import com.algaworks.algafood.service.impl.CozinhaServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi{

	@Autowired
	private CozinhaServiceImpl service;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;  

	@GetMapping
	public Page<CozinhaDto> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Listando cozinhas");
		Page<Cozinha> cozinhasPage = service.findAll(pageable);
		
		List<CozinhaDto> cozinhaDto = cozinhaModelAssembler
				.toCollectionModel(cozinhasPage.getContent());
		
		Page<CozinhaDto> cozinhaPageDtos = new PageImpl<>(cozinhaDto, pageable, 
				cozinhasPage.getTotalElements());
		
		return cozinhaPageDtos;
	}

	@GetMapping("/{id}")
	public CozinhaDto findById(@PathVariable Long id) {
		log.info("Buscando cozinha do id {}", id);	
		return cozinhaModelAssembler.toModel(service.buscar(id));	
	}

	@PutMapping("/{id}")
	public CozinhaDto atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
		log.info("Atualizando cozinha do id {}", id);	
		Cozinha cozinhaAtual = service.buscar(id);

		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		cozinhaAtual = service.save(cozinhaAtual);
	    return cozinhaModelAssembler.toModel(cozinhaAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo cozinha do id {}", id);
		service.deleteById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaDto adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		log.info("Cadastrando nova cozinha de nome {}", cozinhaInput.getNome());
		 Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		 cozinha = service.save(cozinha);
		 return cozinhaModelAssembler.toModel(cozinha);
	}

}
