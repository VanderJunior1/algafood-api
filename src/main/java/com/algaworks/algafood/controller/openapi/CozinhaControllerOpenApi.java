package com.algaworks.algafood.controller.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.dto.CozinhaDto;
import com.algaworks.algafood.dto.input.CozinhaInput;
import com.algaworks.algafood.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Listando cozinhas")
	Page<CozinhaDto> listar(Pageable pageable);

	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = ApiError.class)
		})
	CozinhaDto findById(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long id);

	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = ApiError.class)
		})
	CozinhaDto atualizar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long id, 
			@ApiParam(name = "corpo",value = "Representação de uma cozinha com novos dados", required = true)
			CozinhaInput cozinhaInput);

	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = ApiError.class)
		})
	void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra uma nova cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
		})
	CozinhaDto adicionar(
			@ApiParam(name = "corpo",value = "Representação de uma nova cozinha", required = true)
			CozinhaInput cozinhaInput);
}
