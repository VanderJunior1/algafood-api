package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Grupo;

public interface GrupoService {

	public List<Grupo> findAll();

	public void deleteById(Long id);

	public Grupo save(Grupo grupo);

	public Optional<Grupo> findById(Long id);

	public Grupo buscar(Long id);

	void associarPermissao(Long grupoId, Long permissaoId);

	void desassociarPermissao(Long grupoId, Long permissaoId);
}
