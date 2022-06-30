package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.repository.RestauranteRepository;
import com.algaworks.algafood.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código %d não pode ser removida";
	private static final String MSG_RESTAURANTE_EM_USO = "Não existe código de cadastro para o restaurante: %d";
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaServiceImpl cozinhaServiceImpl;

	@Override
	public List<Restaurante> findAll() {
		return restauranteRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		try {
			restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_EM_USO, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
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
		Restaurante result = restauranteRepository.findById(id).get();
		return result;
	}

}
