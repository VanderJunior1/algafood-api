package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Grupo;
import com.algaworks.algafood.domain.Usuario;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.repository.UsuarioRepository;
import com.algaworks.algafood.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String MSG_USUARIO_EM_USO = "Usuario de código %d não pode ser removido";

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoServiceImpl grupoServiceImpl;

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		try {
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscar(usuarioId);

		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		usuario.setSenha(novaSenha);
	}

	@Override
	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new UsuarioNaoEncontradoException(id));
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	@Override
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscar(usuarioId);
		Grupo grupo = grupoServiceImpl.buscar(grupoId);
		usuario.adicionarGrupo(grupo);
	}
	
	@Override
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscar(usuarioId);
		Grupo grupo = grupoServiceImpl.buscar(grupoId);
		usuario.removerGrupo(grupo);
	}

}
