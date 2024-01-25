package com.akash.cluster;


import com.akash.client.error.ErrorMessage;
import com.akash.client.exception.DataInvalidException;
import com.akash.client.exception.ResponseInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClusterFinderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataInvalidException.class})
    protected ResponseEntity<ErrorMessage> dataInvalidExceptionHandler(DataInvalidException dataInvalidException, WebRequest webRequest){
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ErrorMessage(HttpStatus.BAD_REQUEST, dataInvalidException.getMessage()));
    }
    @ExceptionHandler(value = {ResponseInvalidException.class})
    protected ResponseEntity<ErrorMessage> dataInvalidExceptionHandler(ResponseInvalidException responseInvalidException, WebRequest webRequest){
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ErrorMessage(HttpStatus.BAD_REQUEST, responseInvalidException.getMessage()));
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<ErrorMessage> dataInvalidExceptionHandler(NullPointerException nullPointerException , WebRequest webRequest){
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ErrorMessage(HttpStatus.BAD_REQUEST, nullPointerException.getMessage()));
    }

}
