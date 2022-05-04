package com.cognixia.jump.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException resourceNotFound(ResourceNotFoundException e){
        return new ResourceNotFoundException( LocalDateTime.now());
    }
	
	
	@ExceptionHandler(value = AlreadyExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public AlreadyExistedException alreadyExistedException(AlreadyExistedException e) {
		return new AlreadyExistedException();
	}
	

}
