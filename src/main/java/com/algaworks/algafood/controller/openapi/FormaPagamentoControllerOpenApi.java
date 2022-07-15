package com.algaworks.algafood.controller.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.dto.FormaPagamentoDto;
import com.algaworks.algafood.dto.input.FormaPagamentoInput;
import com.algaworks.algafood.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de Pagamentos")
public interface FormaPagamentoControllerOpenApi {
	
	@ApiOperation("Listando Formas de Pagamentos")
	Page<FormaPagamentoDto> listar(Pageable pageable);

	@ApiOperation("Busca uma Forma de Pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da Forma de Pagamento inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrado", response = ApiError.class)
		})
	FormaPagamentoDto findById(
			@ApiParam(value = "ID de uma Forma de Pagamento", example = "1", required = true)
			Long id);

	@ApiOperation("Atualiza uma Forma de Pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de Pagamento atualizada", response = ApiError.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrada", response = ApiError.class)
		})
	FormaPagamentoDto atualizar(
			@ApiParam(value = "ID de uma Forma de Pagamento", example = "1", required = true)
			Long id, 
			@ApiParam(name = "corpo",value = "Representação de uma Forma de Pagamento com novos dados", required = true)
			FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Cadastra uma nova Forma de Pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de Pagamento cadastrado")
		})
	FormaPagamentoDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de uma nova Forma de Pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Exclui uma Forma de Pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de Pagamento excluído", response = ApiError.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrado", response = ApiError.class)
		})
    void remover(
    		@ApiParam(value = "ID de uma Forma de Pagamento", example = "1", required = true)
    		Long formaPagamentoId);


}
