package com.algaworks.algafood.controller;

import java.util.List;

import javax.transaction.Transactional;

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

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@GetMapping
	public List<Estado> listar() {
		return estadoServiceImpl.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Estado estado  = estadoServiceImpl.buscar(id);
		if (estado  == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estado );
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Estado> atualizar(@RequestBody Estado estado) {
		Estado estadoAtual = estadoServiceImpl.buscar(estado.getId());
		if (estadoAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(estado, estadoAtual, "id");
		estadoServiceImpl.save(estadoAtual);
		return ResponseEntity.ok(estadoAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		estadoServiceImpl.deleteById(id);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		Estado salvo = estadoServiceImpl.save(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
