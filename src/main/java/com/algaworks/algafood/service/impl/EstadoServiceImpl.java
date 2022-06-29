package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.repository.EstadoRepository;
import com.algaworks.algafood.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		estadoRepository.deleteById(id);
	}

	@Override
	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public Optional<Estado> findById(Long id) {
		return estadoRepository.findById(id);
	}

	@Override
	public Estado buscar(Long id) {
		Estado result = estadoRepository.findById(id).get();
		return result;
	}

}
