package com.angelochiarella.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.angelochiarella.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice // Responsável por tratar possiveis errors
public class ResourceExceptionHandler
{
	@ExceptionHandler(ObjectNotFoundException.class) // Padrão para Identificar a Exception
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
	{
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
