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

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.service.impl.CozinhaServiceImpl;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaServiceImpl service;

	@GetMapping
	public List<Cozinha> listar() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
		Cozinha retorno = service.buscar(id);
		if (retorno == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(retorno);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = service.buscar(cozinha.getId());
		if (cozinhaAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		service.save(cozinhaAtual);
		return ResponseEntity.ok(cozinhaAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		service.deleteById(id);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		Cozinha salvo = service.save(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
