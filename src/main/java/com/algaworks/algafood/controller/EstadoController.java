package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.algaworks.algafood.service.impl.EstadoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@GetMapping
	public List<Estado> listar() {
		log.info("Listando estados");
		return estadoServiceImpl.findAll();
	}

	@GetMapping("/{id}")
	public Estado findById(@PathVariable Long id) {
		log.info("Buscando estado do id {}", id);
		Estado estado = estadoServiceImpl.buscar(id);
		return estado;
	}

	@PutMapping()
	public Estado atualizar(@RequestBody @Valid Estado estado) {
		log.info("Atualizando estado do id {}", estado.getId());
		Estado estadoAtual = estadoServiceImpl.buscar(estado.getId());

		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoServiceImpl.save(estadoAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo estado do id {}", id);
		estadoServiceImpl.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody @Valid Estado estado) {
		log.info("Cadastrando novo estado de nome {}", estado.getNome());
		Estado salvo = estadoServiceImpl.save(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
