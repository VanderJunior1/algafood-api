package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.FormaPagamento;
import com.algaworks.algafood.dto.FormaPagamentoDisassembler;
import com.algaworks.algafood.dto.FormaPagamentoDto;
import com.algaworks.algafood.dto.FormaPagamentoModelAssembler;
import com.algaworks.algafood.dto.input.FormaPagamentoInput;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.security.CheckSecurity;
import com.algaworks.algafood.service.impl.FormaPagamentoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi{

	@Autowired
	private FormaPagamentoServiceImpl formaPagamentoServiceImpl;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoDisassembler formaPagamentoDisassembler;

	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping
	public Page<FormaPagamentoDto> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Listando Forma de Pagamentos");	
		Page<FormaPagamento> cozinhasPage = formaPagamentoServiceImpl.findAll(pageable);
		List<FormaPagamentoDto> formaPagamentoDtos =  formaPagamentoModelAssembler
				.toCollectionModel(formaPagamentoServiceImpl.findAll());
		
		Page<FormaPagamentoDto> formaPagamentoPageDtos = new PageImpl<>(formaPagamentoDtos, pageable, 
				cozinhasPage.getTotalElements());
		
		return formaPagamentoPageDtos;
	}

	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping("/{id}")
	public FormaPagamentoDto findById(@PathVariable Long id) {
		log.info("Buscando forma de pagamento do id {}", id);
		FormaPagamento formaPagamento =  formaPagamentoServiceImpl.buscar(id);
		return formaPagamentoModelAssembler.toModel(formaPagamento);		
	}

	@CheckSecurity.FormasPagamento.PodeEditar
	@PutMapping("/{id}")
	public FormaPagamentoDto atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		log.info("Atualizando forma de pagamento de id {}", id);
		FormaPagamento formaPagamentoAtual = formaPagamentoServiceImpl.buscar(id);

		formaPagamentoDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		formaPagamentoAtual = formaPagamentoServiceImpl.save(formaPagamentoAtual);
		return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
	}

	@CheckSecurity.FormasPagamento.PodeEditar
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public FormaPagamentoDto adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		log.info("Cadastrando nova forma de pagamento de nome {}", formaPagamentoInput.getDescricao());
		try {
			FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);
			return formaPagamentoModelAssembler.toModel(formaPagamentoServiceImpl.save(formaPagamento));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoServiceImpl.deleteById(formaPagamentoId);	
    }
	
}

