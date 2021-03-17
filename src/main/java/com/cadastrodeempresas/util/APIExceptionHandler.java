package com.cadastrodeempresas.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var fields =  new ArrayList<ExceptionType.Field>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String name = ((FieldError) error).getField();
			
			fields.add(new ExceptionType.Field(name, message));
		}
		
		var exceptionType = new ExceptionType();
		exceptionType.setStatus(status.value());
		exceptionType.setTitle("Um ou mais campos estão inválidos");
		exceptionType.setDateTime(LocalDateTime.now());
		exceptionType.setFields(fields);
		
		return super.handleExceptionInternal(ex, exceptionType, headers, status, request);
	}
	
}
