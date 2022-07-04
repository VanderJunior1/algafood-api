package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.repository.EstadoRepository;
import com.algaworks.algafood.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida";
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		try {
			estadoRepository.deleteById(id);
			estadoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public Optional<Estado> findById(Long id) {
		return estadoRepository.findById(id);
	}

	@Override
	public Estado buscar(Long id) {
		return estadoRepository.findById(id).orElseThrow(
				()-> new EstadoNaoEncontradoException(id));
	}

}
