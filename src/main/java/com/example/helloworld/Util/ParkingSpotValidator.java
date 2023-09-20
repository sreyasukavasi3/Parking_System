package com.example.helloworld.Util;

import com.example.helloworld.model.ParkingSpot;

public class ParkingSpotValidator {
    public static boolean isValidParkingSpot(ParkingSpot parkingSpot){
        if(parkingSpot.getPname()==null || parkingSpot.getPname()=="")
            return false;
        else return true;
    }
}
