package com.algaworks.algafood.controller.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.dto.GrupoDto;
import com.algaworks.algafood.dto.input.GrupoInput;
import com.algaworks.algafood.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	
	@ApiOperation("Listando grupos")
	Page<GrupoDto> listar(Pageable pageable);

	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
		})
	GrupoDto findById(
			@ApiParam(value = "ID de um grupo", example = "1", required = true) 
			Long id);

	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado", response = ApiError.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
		})
	GrupoDto atualizar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true) 
			Long id,
			@ApiParam(name = "corpo",value = "Representação de um grupo com novos dados", required = true)
			GrupoInput grupoInput);

	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído", response = ApiError.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
		})
	void remover(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra um novo grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado")
		})
	GrupoDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de um novo grupo", required = true) 
			GrupoInput grupoInput );

}
