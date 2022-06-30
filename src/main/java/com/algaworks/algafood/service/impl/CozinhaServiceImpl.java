package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.service.CozinhaService;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe código de cadastro para a cozinha: %d";
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Override
	public List<Cozinha> findAll() {
		return cozinhaRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
		return cozinhaRepository.findById(id).orElseThrow(
				()-> new EntidadeNaoEncontradaException(
						String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
	}

}
