package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);

	public void deleteById(Long id);

	public Usuario save(Usuario usuario);

	public Optional<Usuario> findById(Long id);

	public Usuario buscar(Long id);
	
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha);

	void desassociarGrupo(Long usuarioId, Long grupoId);

	void associarGrupo(Long usuarioId, Long grupoId);
}
