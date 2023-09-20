package com.example.helloworld.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id){
        super("User id not found: " + id);
    }
}
