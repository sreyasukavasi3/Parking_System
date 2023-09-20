package com.example.helloworld.Exception;

public class ParkingSpotNotFoundException extends RuntimeException{
    public ParkingSpotNotFoundException(long pid){

        super("Parking Spot not found with this cost: " + pid);
    }

    public ParkingSpotNotFoundException(){

        super("Parking Spot not found");
    }

}
