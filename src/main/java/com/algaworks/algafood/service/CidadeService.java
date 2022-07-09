package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Cidade;

public interface CidadeService {

	public List<Cidade> findAll();

	public void deleteById(Long id);

	public Cidade save(Cidade cozinha);

	public Optional<Cidade> findById(Long id);

	public Cidade buscar(Long id);
	
	public Page<Cidade> findAll(Pageable pageable);
}
