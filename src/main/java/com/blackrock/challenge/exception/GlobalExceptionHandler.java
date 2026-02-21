package com.blackrock.challenge.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blackrock.challenge.model.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {

		ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).errorCode(ex.getErrorCode())
				.timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

		ErrorResponse error = ErrorResponse.builder().message(ex.getBindingResult().getFieldError().getDefaultMessage())
				.errorCode("VALIDATION_ERROR").timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleBadRequest(Exception ex) {
		return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
	}


}