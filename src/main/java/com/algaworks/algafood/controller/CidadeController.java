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

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.service.impl.CidadeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;

	@GetMapping
	public List<Cidade> listar() {
		log.info("Listando cidades");		
		return cidadeServiceImpl.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id) {
		log.info("Buscando cidade do id {}", id);		
		Cidade cidade = cidadeServiceImpl.buscar(id);
		if (cidade == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cidade);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade) {
		log.info("Atualizando cidade do id {}", cidade.getId());
		Cidade cidadeAtual = cidadeServiceImpl.buscar(cidade.getId());
		if (cidadeAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		cidadeServiceImpl.save(cidadeAtual);
		return ResponseEntity.ok(cidadeAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		cidadeServiceImpl.deleteById(id);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
		log.info("Cadastrando nova cidade de nome {}", cidade.getNome());
		Cidade salvo = cidadeServiceImpl.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
