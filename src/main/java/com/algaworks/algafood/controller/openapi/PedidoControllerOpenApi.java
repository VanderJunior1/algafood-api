package com.algaworks.algafood.controller.openapi;

import java.util.List;

import org.springframework.beans.factory.parsing.Problem;

import com.algaworks.algafood.dto.PedidoDto;
import com.algaworks.algafood.dto.PedidoResumoDto;
import com.algaworks.algafood.dto.input.PedidoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Listando grupos")
	public List<PedidoResumoDto> listar();
	
	@ApiOperation("Busca um pedido por código")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	public PedidoDto buscar(String codigoPedido);
	
	@ApiOperation("Registra um pedido")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Pedido registrado"),
    })
	public PedidoDto adicionar(PedidoInput pedidoInput);

}
