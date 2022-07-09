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

import com.algaworks.algafood.domain.FormaPagamento;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.repository.FormaPagamentoRepository;
import com.algaworks.algafood.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
	
	private static final String MSG_FORMA_PGTO_EM_USO = "Forma de pagamento de código %d não pode ser removida";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Override
	public List<FormaPagamento> findAll() {
		return formaPagamentoRepository.findAll();
	}
	
	@Override
	public Page<FormaPagamento> findAll(Pageable pageable) {
		return formaPagamentoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	@Override
	public Optional<FormaPagamento> findById(Long id) {
		return formaPagamentoRepository.findById(id);
	}

	@Override
	public FormaPagamento buscar(Long id) {
		return findById(id).orElseThrow(
				()-> new FormaPagamentoNaoEncontradaException(id));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PGTO_EM_USO, id));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
