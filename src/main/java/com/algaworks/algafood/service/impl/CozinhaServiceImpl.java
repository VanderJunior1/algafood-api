package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.service.CozinhaService;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Override
	public List<Cozinha> findAll() {
		return cozinhaRepository.findAll();
	}
	
	@Override
	public Page<Cozinha> findAll(Pageable pageable) {
		return cozinhaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
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
				()-> new CozinhaNaoEncontradaException(id));
	}

}
