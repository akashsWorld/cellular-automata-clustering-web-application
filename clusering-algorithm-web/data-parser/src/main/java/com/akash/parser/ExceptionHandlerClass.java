package com.akash.parser;

import com.akash.client.error.ErrorMessage;
import com.akash.client.exception.DataInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<ErrorMessage> nullPointerExceptionHandle(NullPointerException nullPointer, WebRequest request){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, nullPointer.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(value = {DataInvalidException.class})
    protected ResponseEntity<ErrorMessage> dataInvalidExceptionHandler(DataInvalidException dataInvalidException,
                                                                       WebRequest request){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE,"Data invalid to done this operation");

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
    }
}
