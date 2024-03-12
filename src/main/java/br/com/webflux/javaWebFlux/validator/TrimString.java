package br.com.webflux.javaWebFlux.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { TrimStringValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimString {
	
	/*
		This implements our @TrimString annotation, customized to 
		validate spaces before and after a string, such as in the name.
		this will initially be used in the UserRequest name field.
	*/

	String message() default "Field cannot have blank at the beginning or at end.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}