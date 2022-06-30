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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaServiceImpl service;

	@GetMapping
	public List<Cozinha> listar() {
		log.info("Listando cozinhas");
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Cozinha findById(@PathVariable Long id) {
		log.info("Buscando cozinha do id {}", id);	
		return service.buscar(id);	
	}

	@PutMapping()
	@Transactional
	public Cozinha atualizar(@RequestBody Cozinha cozinha) {
		log.info("Atualizando cozinha do id {}", cozinha.getId());	
		Cozinha cozinhaSalva = service.buscar(cozinha.getId());

		BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");
		return service.save(cozinhaSalva);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		log.info("Removendo cozinha do id {}", id);
		service.deleteById(id);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		log.info("Cadastrando nova cozinha de nome {}", cozinha.getNome());
		Cozinha salvo = service.save(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
