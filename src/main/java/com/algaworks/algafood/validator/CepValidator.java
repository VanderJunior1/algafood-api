package com.algaworks.algafood.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<Cep, String> {

	  @Override
	  public void initialize(Cep constraintAnnotation) {

	  }

	  @Override
		public boolean isValid(String cep, final ConstraintValidatorContext context) {
			boolean result = false;
			if ( cep == null || "".equals(cep) ) {
				result = true;
			} else {
				Pattern pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");  
				Matcher matcher = pattern.matcher(cep);
				result = matcher.find();
			}
			return result;
		}

}
