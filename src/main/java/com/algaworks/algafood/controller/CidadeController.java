package com.algaworks.algafood.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.exception.NegocioException;
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
	public Cidade findById(@PathVariable Long id) {
		log.info("Buscando cidade do id {}", id);		
		return cidadeServiceImpl.buscar(id);
	}

	@PutMapping()
	@Transactional
	public Cidade atualizar(@RequestBody Cidade cidade) {
		log.info("Atualizando cidade do id {}", cidade.getId());
		try {
			Cidade cidadeAtual = cidadeServiceImpl.buscar(cidade.getId());
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cidadeServiceImpl.save(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		cidadeServiceImpl.deleteById(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@Transactional
	public Cidade adicionar(@RequestBody Cidade cidade) {
		log.info("Cadastrando nova cidade de nome {}", cidade.getNome());
		try {
			return  cidadeServiceImpl.save(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
