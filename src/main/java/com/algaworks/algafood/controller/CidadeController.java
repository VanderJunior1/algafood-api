package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.dto.CidadeDto;
import com.algaworks.algafood.dto.CidadeInputDisassembler;
import com.algaworks.algafood.dto.CidadeModelAssembler;
import com.algaworks.algafood.dto.input.CidadeInput;
import com.algaworks.algafood.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.service.impl.CidadeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@ApiOperation("Listando cidades")
	@GetMapping
	public Page<CidadeDto> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Listando cidades");	
		Page<Cidade> cozinhasPage = cidadeServiceImpl.findAll(pageable);
		List<CidadeDto> cidadeDtos =  cidadeModelAssembler
				.toCollectionModel(cidadeServiceImpl.findAll());
		
		Page<CidadeDto> cidadePageDtos = new PageImpl<>(cidadeDtos, pageable, 
				cozinhasPage.getTotalElements());
		
		return cidadePageDtos;
	}

	@ApiOperation("Busca uma cidade por ID")
	@GetMapping("/{id}")
	public CidadeDto findById(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			@PathVariable Long id) {
		log.info("Buscando cidade do id {}", id);		
		return cidadeModelAssembler.toModel(cidadeServiceImpl.buscar(id));
	}

	@ApiOperation("Atualiza uma cidade por ID")
	@PutMapping("/{id}")
	public CidadeDto atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			@PathVariable Long id,
			@ApiParam(name = "corpo",value = "Representação de uma cidade com novos dados", required = true)
			@RequestBody @Valid CidadeInput cidadeInput) {
		log.info("Atualizando cidade do id {}", id);
		try {
			Cidade cidadeAtual = cidadeServiceImpl.buscar(id);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual = cidadeServiceImpl.save(cidadeAtual);
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@ApiOperation("Remove uma cidade por ID")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			@PathVariable Long id) {
		cidadeServiceImpl.deleteById(id);
	}

	@ApiOperation("Cadastra uma cidade")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public CidadeDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de uma nova cidade", required = true) 
			@RequestBody @Valid CidadeInput cidadeInput ) {
		log.info("Cadastrando nova cidade de nome {}", cidadeInput.getNome());
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidadeServiceImpl.save(cidade);
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
