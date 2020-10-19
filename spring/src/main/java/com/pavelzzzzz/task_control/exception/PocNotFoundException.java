package com.pavelzzzzz.task_control.exception;

public class PocNotFoundException extends PocException {

    public PocNotFoundException() {
    }

    public PocNotFoundException(String message) {
        super(message);
    }

    public PocNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PocNotFoundException(Throwable cause) {
        super(cause);
    }

    public PocNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
