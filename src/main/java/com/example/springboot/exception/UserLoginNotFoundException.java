package com.example.springboot.exception;

public class UserLoginNotFoundException extends RuntimeException{
    public UserLoginNotFoundException() {
    }

    public UserLoginNotFoundException(String message) {
        super(message);
    }

    public UserLoginNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserLoginNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
