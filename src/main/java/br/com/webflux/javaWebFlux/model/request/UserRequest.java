package br.com.webflux.javaWebFlux.model.request;

import br.com.webflux.javaWebFlux.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
		
		/*
		 For validations, also use the @valid annotation in the controller interface.
		 */
		
		@TrimString
		@Size(min = 3, max = 50, message = "Must not be null or empty")
		@NotBlank(message = "Must not be null or empty.")
		String name,
		
		@TrimString
		@Email(message = "Invalid email.")
		String email,
		
		@TrimString
		@NotBlank(message = "Must not be null or empty.")
		@Size(min = 3, max = 20, message = "Must be between 3 and 20 characters.")
		String password
){}
