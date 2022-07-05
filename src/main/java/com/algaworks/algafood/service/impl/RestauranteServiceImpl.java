package com.algaworks.algafood.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.domain.FormaPagamento;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.repository.RestauranteRepository;
import com.algaworks.algafood.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private FormaPagamentoServiceImpl formaPagamentoServiceImpl;
	
	@Autowired
	private CozinhaServiceImpl cozinhaServiceImpl;
	
	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;

	@Override
	public List<Restaurante> findAll() {
		return restauranteRepository.findAll();
	}

	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		Cozinha cozinha = cozinhaServiceImpl.buscar(restaurante.getCozinha().getId());
		Cidade cidade = cidadeServiceImpl.buscar(restaurante.getEndereco().getCidade().getId());
		restaurante.getEndereco().setCidade(cidade);
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

	@Override
	public Optional<Restaurante> findById(Long id) {
		return restauranteRepository.findById(id);
	}

	@Override
	public Restaurante buscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(
				()-> new RestauranteNaoEncontradoException(id));
	}

	@Override
	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.ativar();
	}
	
	@Override
	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.inativar();
	}

	@Override
	@Transactional
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoServiceImpl.buscar(formaPagamentoId);
		restaurante.associarFormaPagamento(formaPagamento);
	}
	
	@Override
	@Transactional
	public void dessassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoServiceImpl.buscar(formaPagamentoId);
		restaurante.dessassociarFormaPagamento(formaPagamento);
	}
	
}
