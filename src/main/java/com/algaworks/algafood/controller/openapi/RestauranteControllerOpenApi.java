package com.algaworks.algafood.controller.openapi;

import java.util.List;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestParam;

import com.algaworks.algafood.dto.RestauranteDto;
import com.algaworks.algafood.dto.input.RestauranteDtoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation("Listando Restaurantes")
	public MappingJacksonValue listar(@RequestParam(required = false) String projecao);
	

	@ApiOperation("Busca um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	public RestauranteDto findById(Long id);

	@ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Restaurante atualizado"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	public RestauranteDto atualizar(Long id, RestauranteDtoInput restauranteDtoInput);

	@ApiOperation("Cadastra um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurante cadastrado"),
    })
	public RestauranteDto adicionar(RestauranteDtoInput restauranteDtoInput);
	
	 @ApiOperation("Ativa um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	public void ativar(Long id);
	
	 @ApiOperation("Inativa um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	public void inativar(Long id);
	
	 @ApiOperation("Inativa múltiplos restaurantes")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
	    })
	public void inativar(List<Long> restauranteIds);
	
	 @ApiOperation("Desativa múltiplos restaurantes")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes desativados com sucesso")
	    })
	public void desativarMultiplos(List<Long> restauranteIds);
	
	 @ApiOperation("Abre um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	public void abrir(Long id);
	
	 @ApiOperation("Fecha um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	public void fechar(Long id);

}
