package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Cidade;

public interface CidadeService {

	public List<Cidade> findAll();

	public void deleteById(Long id);

	public Cidade save(Cidade cozinha);

	public Optional<Cidade> findById(Long id);

	public Cidade buscar(Long id);
}
