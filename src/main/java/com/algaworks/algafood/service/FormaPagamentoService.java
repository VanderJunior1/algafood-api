package com.algaworks.algafood.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.FormaPagamento;

public interface FormaPagamentoService {

	public List<FormaPagamento> findAll();
	
	public void deleteById(Long id);

	public FormaPagamento save(FormaPagamento formaPagamento);

	public Optional<FormaPagamento> findById(Long id);

	public FormaPagamento buscar(Long id);
	

}
