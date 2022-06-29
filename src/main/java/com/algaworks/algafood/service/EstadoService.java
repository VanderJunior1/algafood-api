package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Estado;

public interface EstadoService {

	public List<Estado> findAll();

	public void deleteById(Long id);

	public Estado save(Estado cozinha);

	public Optional<Estado> findById(Long id);

	public Estado buscar(Long id);
}
