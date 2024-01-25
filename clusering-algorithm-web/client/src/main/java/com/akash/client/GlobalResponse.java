package com.akash.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalResponse<T> {
    private T data;
    private HttpStatus httpStatus;
    private String message;
}
