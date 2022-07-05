package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Grupo;
import com.algaworks.algafood.dto.GrupoDto;
import com.algaworks.algafood.dto.GrupoInputDisassembler;
import com.algaworks.algafood.dto.GrupoModelAssembler;
import com.algaworks.algafood.dto.input.GrupoInput;
import com.algaworks.algafood.service.impl.GrupoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoServiceImpl service;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;  

	@GetMapping
	public List<GrupoDto> listar() {
		log.info("Listando grupos");
		return grupoModelAssembler.toCollectionModel(service.findAll());
	}

	@GetMapping("/{id}")
	public GrupoDto findById(@PathVariable Long id) {
		log.info("Buscando grupo de id {}", id);	
		return grupoModelAssembler.toModel(service.buscar(id));	
	}

	@PutMapping("/{id}")
	public GrupoDto atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		log.info("Atualizando grupo de id {}", id);	
		Grupo grupoAtual = service.buscar(id);

		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		grupoAtual = service.save(grupoAtual);
	    return grupoModelAssembler.toModel(grupoAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo grupo do id {}", id);
		service.deleteById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public GrupoDto adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		log.info("Cadastrando nova grupo de nome {}", grupoInput.getNome());
		 Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		 grupo = service.save(grupo);
		 return grupoModelAssembler.toModel(grupo);
	}

}
