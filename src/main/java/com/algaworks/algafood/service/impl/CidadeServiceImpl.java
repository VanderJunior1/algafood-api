package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.repository.CidadeRepository;
import com.algaworks.algafood.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe código de cadastro para a cidade: %d";
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida";


	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@Override
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Cidade save(Cidade cidade) {
		Estado estado = estadoServiceImpl.buscar(cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	@Override
	public Optional<Cidade> findById(Long id) {
		return cidadeRepository.findById(id);
	}

	@Override
	public Cidade buscar(Long id) {
		return cidadeRepository.findById(id).orElseThrow(
				()-> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
	}

}
