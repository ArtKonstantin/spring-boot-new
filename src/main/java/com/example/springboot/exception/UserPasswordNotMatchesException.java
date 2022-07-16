package com.example.springboot.exception;

public class UserPasswordNotMatchesException extends RuntimeException {
    public UserPasswordNotMatchesException() {
    }

    public UserPasswordNotMatchesException(String message) {
        super(message);
    }

    public UserPasswordNotMatchesException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPasswordNotMatchesException(Throwable cause) {
        super(cause);
    }

    public UserPasswordNotMatchesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
