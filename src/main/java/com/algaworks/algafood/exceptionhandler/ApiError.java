package com.algaworks.algafood.exceptionhandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ApiError {

	private LocalDateTime dataHora;
	private String mensagem;

}
