package com.akash.modifier;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity<ErrorMessage> indexOutOfBoundExceptionHandle(IndexOutOfBoundsException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
