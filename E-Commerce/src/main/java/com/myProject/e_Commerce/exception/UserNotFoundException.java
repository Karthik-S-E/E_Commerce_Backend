package com.myProject.e_Commerce.exception;

public class UserNotFoundException extends RuntimeException{
    String message;
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message= message;
    }
}
