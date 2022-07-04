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

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.dto.EstadoDto;
import com.algaworks.algafood.dto.EstadoInputDisassembler;
import com.algaworks.algafood.dto.EstadoModelAssembler;
import com.algaworks.algafood.dto.input.EstadoInput;
import com.algaworks.algafood.service.impl.EstadoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@GetMapping
	public List<EstadoDto> listar() {
		log.info("Listando estados");
		return estadoModelAssembler.toCollectionModel(estadoServiceImpl.findAll());
	}

	@GetMapping("/{id}")
	public EstadoDto findById(@PathVariable Long id) {
		log.info("Buscando estado do id {}", id);
		Estado estado = estadoServiceImpl.buscar(id);
		return estadoModelAssembler.toModel(estado);
	}

	@PutMapping("/{id}")
	public EstadoDto atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
		log.info("Atualizando estado do id {}", id);
		Estado estadoAtual = estadoServiceImpl.buscar(id);
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		estadoAtual = estadoServiceImpl.save(estadoAtual);
		return estadoModelAssembler.toModel(estadoAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo estado do id {}", id);
		estadoServiceImpl.deleteById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoDto adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		log.info("Cadastrando novo estado de nome {}", estadoInput.getNome());
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		Estado salvo = estadoServiceImpl.save(estado);
		return estadoModelAssembler.toModel(salvo);
	}

}
