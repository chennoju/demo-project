package com.example.demo;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exceptions.UserNotFoundException;
@ControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {
		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception{
			ErrorDetails error=new ErrorDetails(LocalDate.now(),ex.getMessage()+"hi",request.getDescription(false));
			
			return new ResponseEntity(error,HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		@ExceptionHandler(UserNotFoundException.class)
		public final ResponseEntity<Object> handleEmployeeNotFoundExceptions(Exception ex, WebRequest request) throws Exception{
			ErrorDetails error=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
			return new ResponseEntity(error,HttpStatus.NOT_FOUND);
			
		}
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

			ErrorDetails error=new ErrorDetails(LocalDate.now(),ex.getFieldError().getDefaultMessage(),request.getDescription(false));
			
			return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
		}

}
