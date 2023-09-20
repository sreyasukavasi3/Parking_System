package com.example.helloworld.Exception;

public class IssuedParkingSpotsNotFoundException extends RuntimeException {
    public IssuedParkingSpotsNotFoundException(long users_id){
        super("Parking Spot not found with this User ID: " + users_id);
    }

}
