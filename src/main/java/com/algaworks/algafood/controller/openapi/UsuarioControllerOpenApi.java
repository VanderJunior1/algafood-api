package com.algaworks.algafood.controller.openapi;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.dto.UsuarioDto;
import com.algaworks.algafood.dto.input.SenhaInput;
import com.algaworks.algafood.dto.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	PagedModel<UsuarioDto> listar(Pageable pageable);

	@ApiOperation("Busca um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
	UsuarioDto findById(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id);

	
	@ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
	UsuarioDto atualizar(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id, 
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			UsuarioInput usuarioInput);

	
	@ApiOperation("Remove um usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
	void remover(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra um usuário")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
	UsuarioDto adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
			UsuarioInput usuarioInput);
	
	@ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
	void alterarSenha(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long usuarioId, 
			@ApiParam(name = "corpo", value = "Representação de uma nova senha", 
            required = true)
			SenhaInput senha); 
	
}
