package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Restaurante;

public interface RestauranteService {

	public List<Restaurante> findAll();

	public void deleteById(Long id);

	public Restaurante save(Restaurante restaurante);

	public Optional<Restaurante> findById(Long id);

	public Restaurante buscar(Long id);

}
