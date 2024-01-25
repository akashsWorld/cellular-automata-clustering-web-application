package com.akash.client.exception;

public class DataInvalidException extends RuntimeException{
    public DataInvalidException() {
        super();
    }

    public DataInvalidException(String message) {
        super(message);
    }

    public DataInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInvalidException(Throwable cause) {
        super(cause);
    }

    protected DataInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
