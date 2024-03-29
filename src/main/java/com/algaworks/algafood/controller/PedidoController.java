package com.algaworks.algafood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.PedidoControllerOpenApi;
import com.algaworks.algafood.domain.Pedido;
import com.algaworks.algafood.domain.Usuario;
import com.algaworks.algafood.dto.PedidoDto;
import com.algaworks.algafood.dto.PedidoInputDisassembler;
import com.algaworks.algafood.dto.PedidoModelAssembler;
import com.algaworks.algafood.dto.PedidoResumoDto;
import com.algaworks.algafood.dto.PedidoResumoModelAssembler;
import com.algaworks.algafood.dto.input.PedidoInput;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.security.AlgaSecurity;
import com.algaworks.algafood.security.CheckSecurity;
import com.algaworks.algafood.service.impl.EmissaoPedidoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private EmissaoPedidoServiceImpl emissaoPedidoServiceImpl;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired
	PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	@CheckSecurity.Pedidos.PodeConsultar
	@GetMapping
	public PagedModel<PedidoResumoDto> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Buscando lista de pedidos");
		Page<Pedido> pedidosPage = emissaoPedidoServiceImpl.findAll(pageable);
		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@CheckSecurity.Pedidos.PodeConsultar
	@GetMapping("/{codigoPedido}")
	public PedidoDto buscar(@PathVariable String codigoPedido) {
		log.info("Buscando lista de pedidos de código: {}", codigoPedido);

		Pedido pedido = emissaoPedidoServiceImpl.buscar(codigoPedido);
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@CheckSecurity.Pedidos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDto adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
	    try {
	        Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

	        novoPedido.setCliente(new Usuario());
	        novoPedido.getCliente().setId(algaSecurity.getUsuarioId());
	        novoPedido = emissaoPedidoServiceImpl.emitir(novoPedido);

	        return pedidoModelAssembler.toModel(novoPedido);
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

}
