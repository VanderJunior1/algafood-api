package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Cozinha;

public interface CozinhaService {

	public List<Cozinha> findAll();

	public void deleteById(Long id);

	public Cozinha save(Cozinha cozinha);

	public Optional<Cozinha> findById(Long id);

	public Cozinha buscar(Long id);

	public Page<Cozinha> findAll(Pageable pageable);

}
