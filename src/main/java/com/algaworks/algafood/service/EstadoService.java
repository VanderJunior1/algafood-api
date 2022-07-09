package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Estado;

public interface EstadoService {

	public List<Estado> findAll();

	public void deleteById(Long id);

	public Estado save(Estado cozinha);

	public Optional<Estado> findById(Long id);

	public Estado buscar(Long id);
	
	public Page<Estado> findAll(Pageable pageable);
}
