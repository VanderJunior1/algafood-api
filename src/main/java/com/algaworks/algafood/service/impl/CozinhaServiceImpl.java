package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.service.CozinhaService;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Override
	public List<Cozinha> findAll() {
		return cozinhaRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		cozinhaRepository.deleteById(id);

	}

	@Override
	public Cozinha save(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Override
	public Optional<Cozinha> findById(Long id) {
		return cozinhaRepository.findById(id);
	}

	@Override
	public Cozinha buscar(Long id) {
		return cozinhaRepository.findById(id).get();
	}

}
