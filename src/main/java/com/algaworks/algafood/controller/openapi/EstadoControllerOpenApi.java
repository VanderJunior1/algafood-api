package com.algaworks.algafood.controller.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.dto.EstadoDto;
import com.algaworks.algafood.dto.input.EstadoInput;
import com.algaworks.algafood.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Listando estados")
	public Page<EstadoDto> listar(Pageable pageable);

	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = ApiError.class)
		})
	public EstadoDto findById(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			Long id);

	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado", response = ApiError.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = ApiError.class)
		})
	public EstadoDto atualizar(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			Long id,
			@ApiParam(name = "corpo",value = "Representação de um estado com novos dados", required = true)
			EstadoInput estadoInput);

	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído", response = ApiError.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = ApiError.class)
		})
	public void remover(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra um novo estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")
		})
	public EstadoDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de um novo estado", required = true)
			EstadoInput estadoInput);

}
