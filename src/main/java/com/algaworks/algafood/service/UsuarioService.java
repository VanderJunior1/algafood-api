package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();

	public void deleteById(Long id);

	public Usuario save(Usuario usuario);

	public Optional<Usuario> findById(Long id);

	public Usuario buscar(Long id);
	
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha);
}
