package com.algaworks.algafood.controller.openapi;

import org.springframework.beans.factory.parsing.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirmação de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	void confirmar(
			@ApiParam(value = "Código do pedido", example = "9c70a176-fdf9-11ec-848c-641c679e0b8d", required = true)
			String codigoPedido);

	@ApiOperation("Cancelamento de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	void cancelar(
			@ApiParam(value = "Código do pedido", example = "9c70a176-fdf9-11ec-848c-641c679e0b8d", required = true)
			String codigoPedido);

	@ApiOperation("Registrar entrega de pedido")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
	public void entregar(
			@ApiParam(value = "Código do pedido", example = "9c70a176-fdf9-11ec-848c-641c679e0b8d", required = true)
			String codigoPedido);

}
