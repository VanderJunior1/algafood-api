package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Restaurante;

public interface RestauranteService {

	public List<Restaurante> findAll();

	public Restaurante save(Restaurante restaurante);

	public Optional<Restaurante> findById(Long id);

	public Restaurante buscar(Long id);
	
	public void ativar(Long id);
	
	public void inativar(Long id);

}
