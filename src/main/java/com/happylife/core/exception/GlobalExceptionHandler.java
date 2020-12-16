package com.happylife.core.exception;

import com.happylife.core.common.Response;
import com.happylife.core.exception.uuid.UUIDException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> exception(EntityNotFoundException ex){
        Response response = Response.fail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UUIDException.class)
    public ResponseEntity<Object> exception(UUIDException ex) {
        Response response = Response.fail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Object> exceptin(LoginException ex){
        Response response = Response.fail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
