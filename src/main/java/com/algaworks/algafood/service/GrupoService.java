package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Grupo;

public interface GrupoService {

	public List<Grupo> findAll();

	public void deleteById(Long id);

	public Grupo save(Grupo grupo);

	public Optional<Grupo> findById(Long id);

	public Grupo buscar(Long id);

	void associarPermissao(Long grupoId, Long permissaoId);

	void desassociarPermissao(Long grupoId, Long permissaoId);
	
	public Page<Grupo> findAll(Pageable pageable);
}
