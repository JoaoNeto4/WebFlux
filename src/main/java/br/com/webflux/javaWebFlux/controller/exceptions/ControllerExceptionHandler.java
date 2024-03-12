package br.com.webflux.javaWebFlux.controller.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import reactor.core.publisher.Mono;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(DuplicateKeyException.class)
	ResponseEntity<Mono<StandardError>> duplicateKeyException(
			DuplicateKeyException ex, ServerHttpRequest request
	){
		return ResponseEntity.badRequest()
				.body(Mono.just(
						StandardError.builder()
							.timestamp(now())
							.status(HttpStatus.BAD_REQUEST.value())
							.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
							.message(verifyDupKey(ex.getMessage()))
							.path(request.getPath().toString())
							.build()
				));
	}
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<Mono<ValidationError>> validationError(
			WebExchangeBindException ex, ServerHttpRequest request
	){
		
		/*
		 Used to return a list of custom validation errors relating to request fields. 
		*/
		
		ValidationError error = new ValidationError(
				now(),request.getPath().toString(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Validation error",
				"Error on validation attributes"
		);
		
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(error));
	}
	
	private String verifyDupKey(String message) {
		if(message.contains("email dup key")) {
			return "E-mail already registered";
		}
		return "Dup key exception";
	}

}
