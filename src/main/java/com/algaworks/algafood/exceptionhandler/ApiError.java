package com.algaworks.algafood.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Api Erros")
@JsonInclude(Include.NON_NULL)
@Builder @Getter
public class ApiError {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;

	@ApiModelProperty(example = "https://algafood.com/erro-negocio", position = 10)
	private String type;

	@ApiModelProperty(example = "Violação de regra de negócio", position = 20)
	private String title;

	@ApiModelProperty(example = "Não existe um cadastro de estado com codigo 1", position = 25)
	private String detail;

	@ApiModelProperty(example = "Não existe um cadastro de estado com codigo 1", position = 30)
	private String userMessage;

	@ApiModelProperty(example = "2022-07-11T19:23:50.344+00:00", position = 35)
	private OffsetDateTime timestamp;

	@ApiModelProperty(value = "Objetos ou campos que geraram o erro", position = 40)
	private List<Object> objects;

	@ApiModel("Objeto Problema")
	@Getter
	@Builder
	public static class Object {

		@ApiModelProperty(example = "Estado")
		private String name;

		@ApiModelProperty(example = "Estado não encontrado")
		private String userMessage;
	}

}
