package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.repository.RestauranteRepository;
import com.algaworks.algafood.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaServiceImpl cozinhaServiceImpl;

	@Override
	public List<Restaurante> findAll() {
		return restauranteRepository.findAll();
	}

	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		Cozinha cozinha = cozinhaServiceImpl.buscar(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	@Override
	public Optional<Restaurante> findById(Long id) {
		return restauranteRepository.findById(id);
	}

	@Override
	public Restaurante buscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(
				()-> new RestauranteNaoEncontradoException(id));
	}

	@Override
	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.ativar();
	}
	
	@Override
	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.inativar();
	}

}
