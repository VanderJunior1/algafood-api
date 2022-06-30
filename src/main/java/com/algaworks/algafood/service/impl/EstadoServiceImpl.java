package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.repository.EstadoRepository;
import com.algaworks.algafood.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida";
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe código de cadastro para o estado: %d";
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
		return estadoRepository.findById(id).orElseThrow(
				()-> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}

}
