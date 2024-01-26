package com.akash.client.exception;

public class BoundaryNotFoundException extends RuntimeException{
    public BoundaryNotFoundException() {
        super();
    }

    public BoundaryNotFoundException(String message) {
        super(message);
    }

    public BoundaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoundaryNotFoundException(Throwable cause) {
        super(cause);
    }

    protected BoundaryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
