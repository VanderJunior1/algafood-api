package com.algaworks.algafood.controller.openapi;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.dto.CidadeDto;
import com.algaworks.algafood.dto.input.CidadeInput;
import com.algaworks.algafood.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Listando cidades")
	CollectionModel<CidadeDto> listar();

	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class)
		})
	CidadeDto findById(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long id);

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class)
		})
	CidadeDto atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long id,
			@ApiParam(name = "corpo",value = "Representação de uma cidade com novos dados", required = true)
			CidadeInput cidadeInput);

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class)
		})
	void remover(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
		})
	CidadeDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de uma nova cidade", required = true) 
			 CidadeInput cidadeInput ) ;
	
}
