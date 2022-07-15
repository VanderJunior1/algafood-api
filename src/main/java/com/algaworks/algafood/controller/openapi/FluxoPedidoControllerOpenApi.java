package com.algaworks.algafood.controller.openapi;

import org.springframework.beans.factory.parsing.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirmação de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	void confirmar(String codigoPedido);

	@ApiOperation("Cancelamento de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	void cancelar(String codigoPedido);

	@ApiOperation("Registrar entrega de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	public void entregar(String codigoPedido);

}
