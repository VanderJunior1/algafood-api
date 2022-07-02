package com.algaworks.algafood.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;

	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}

	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.valorField)
					.getReadMethod().invoke(objetoValidacao);
			String descricaoField = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.descricaoField)
					.getReadMethod().invoke(objetoValidacao);
			if (valor != null && descricaoField != null && valor.compareTo(BigDecimal.ZERO) == 0) {
				return descricaoField.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
		} catch (Exception e) {
			throw new ValidationException(e);
		}
		return false;
	}

}
