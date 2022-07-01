package com.algaworks.algafood.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@Pattern(regexp ="(?:https?:\\/\\/)?(?:www\\.|m\\.|mobile\\.|touch\\.|mbasic\\.)?(?:facebook\\.com|fb(?:\\.me|\\.com))\\/(?!$)(?:(?:\\w)*#!\\/)?(?:pages\\/|pg\\/)?(?:photo\\.php\\?fbid=)?(?:[\\w\\-]*\\/)*?(?:\\/)?(?:profile\\.php\\?id=)?([^\\/?&\\s]*)(?:\\/|&|\\?)?.*")
public @interface Facebook {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{Facebook.invalido}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
