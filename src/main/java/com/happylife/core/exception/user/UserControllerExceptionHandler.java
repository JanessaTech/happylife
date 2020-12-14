package com.happylife.core.exception.user;

import com.happylife.core.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler(value = UserFilterParameterException.class)
    public ResponseEntity<Object> exception(UserFilterParameterException ex){
        Response response = Response.fail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
