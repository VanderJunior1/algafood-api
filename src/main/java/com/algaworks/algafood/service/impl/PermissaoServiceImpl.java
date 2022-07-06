package com.algaworks.algafood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Permissao;
import com.algaworks.algafood.exception.PermissaoNaoEncontradaException;
import com.algaworks.algafood.repository.PermissaoRepository;
import com.algaworks.algafood.service.PermissaoService;

@Service
public class PermissaoServiceImpl implements PermissaoService {

	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public Permissao buscar(Long id) {
		return permissaoRepository.findById(id).orElseThrow(
				()-> new PermissaoNaoEncontradaException(id));
	}

}
