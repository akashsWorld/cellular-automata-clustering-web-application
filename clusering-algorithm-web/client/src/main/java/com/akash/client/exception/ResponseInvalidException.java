package com.akash.client.exception;

public class ResponseInvalidException extends RuntimeException {
    public ResponseInvalidException() {
        super();
    }

    public ResponseInvalidException(String message) {
        super(message);
    }

    public ResponseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseInvalidException(Throwable cause) {
        super(cause);
    }

    protected ResponseInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
