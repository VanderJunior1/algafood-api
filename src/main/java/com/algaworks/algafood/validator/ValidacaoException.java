package com.algaworks.algafood.validator;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {
	private static final long serialVersionUID = -7149880019344898298L;
	
	private BindingResult bindingResult;
}
