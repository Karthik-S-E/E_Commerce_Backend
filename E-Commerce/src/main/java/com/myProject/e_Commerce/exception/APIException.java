package com.myProject.e_Commerce.exception;

public class APIException extends RuntimeException{
private static final long SERIAL_VERSION_ID=1L;
    public APIException(String message) {
        super(message);
    }
}
