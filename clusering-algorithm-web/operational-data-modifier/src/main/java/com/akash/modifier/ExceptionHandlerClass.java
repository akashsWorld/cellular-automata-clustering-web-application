package com.akash.modifier;


import com.akash.client.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    protected ResponseEntity<ErrorMessage> indexOutOfBoundExceptionHandle(IndexOutOfBoundsException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<ErrorMessage> nullPointerExceptionHandle(NullPointerException nullPointer, WebRequest request){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, nullPointer.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
