package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.domain.FormaPagamento;

public interface FormaPagamentoService {

	public List<FormaPagamento> findAll();
	
	public void deleteById(Long id);

	public FormaPagamento save(FormaPagamento formaPagamento);

	public Optional<FormaPagamento> findById(Long id);

	public FormaPagamento buscar(Long id);
	
	public Page<FormaPagamento> findAll(Pageable pageable);

}
