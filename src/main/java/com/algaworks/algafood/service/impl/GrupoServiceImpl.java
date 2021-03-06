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

import com.algaworks.algafood.domain.Grupo;
import com.algaworks.algafood.domain.Permissao;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.repository.GrupoRepository;
import com.algaworks.algafood.service.GrupoService;

@Service
public class GrupoServiceImpl implements GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoServiceImpl permissaoServiceImpl;

	@Override
	public List<Grupo> findAll() {
		return grupoRepository.findAll();
	}
	
	@Override
	public Page<Grupo> findAll(Pageable pageable) {
		return grupoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Override
	public Optional<Grupo> findById(Long id) {
		return grupoRepository.findById(id);
	}

	@Override
	public Grupo buscar(Long id) {
		return grupoRepository.findById(id).orElseThrow(
				()-> new GrupoNaoEncontradoException(id));
	}
	
	@Override
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscar(grupoId);
	    Permissao permissao = permissaoServiceImpl.buscar(permissaoId);
	    
	    grupo.removerPermissao(permissao);
	}

	@Override
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscar(grupoId);
	    Permissao permissao = permissaoServiceImpl.buscar(permissaoId);
	    
	    grupo.adicionarPermissao(permissao);
	} 

}
