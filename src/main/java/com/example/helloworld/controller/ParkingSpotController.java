package com.example.helloworld.controller;

import com.example.helloworld.Exception.ParkingSpotNotFoundException;
import com.example.helloworld.Repositoy.IssuedParkingSpotsRepo;
import com.example.helloworld.Repositoy.ParkingSpotRepo;
import com.example.helloworld.Repositoy.UserRepo;
import com.example.helloworld.Util.ParkingSpotValidator;
import com.example.helloworld.model.ParkingSpot;
import com.example.helloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ParkingSpotController {

    @Autowired
    ParkingSpotRepo parkingSpotRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    IssuedParkingSpotsRepo parkRepo;

//    @Autowired
//    private ParkingSpotRepo repo;


    // find all parking spots
    @GetMapping("/api/parkingspots")
    public ResponseEntity<List<ParkingSpot>> findAllParkingSpots(){
        return new ResponseEntity<>(parkingSpotRepo.findAll(), HttpStatus.OK);
    }
//    List<ParkingSpot> findAll(){
//        List<ParkingSpot> list;
//        try{
//            list = parkingSpotRepo.findAll();
//            if(list.size()==0){
//                throw new ParkingSpotNotFoundException();
//            }
//        }
//        catch(ParkingSpotNotFoundException exc){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "No free parking spot found", exc);
//
//        }
//        return list;
//    }

    @PostMapping("/api/parkingspots")
    public ResponseEntity<ParkingSpot> saveParkingSpot(@RequestBody ParkingSpot parkingSpot) throws Exception{
        if(ParkingSpotValidator.isValidParkingSpot(parkingSpot))
            return new ResponseEntity<>(parkingSpotRepo.save(parkingSpot), HttpStatus.CREATED);
        else throw new Exception();
    }

    @GetMapping("/api/parkingspots/pid/{pid}")
    public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable long pid){
        Optional<ParkingSpot> parkingSpot = parkingSpotRepo.findById(pid);
        if(parkingSpot.isPresent()){
            return new ResponseEntity<>(parkingSpot.get(), HttpStatus.OK);
        }
        else{
            throw new ParkingSpotNotFoundException(pid);
        }
    }

    @GetMapping("/api/parkingspots/pcost/{pCost}")
    public ResponseEntity<ParkingSpot> getParkingSpotCost(@PathVariable long pcost){
        Optional<ParkingSpot> parkingSpot = parkingSpotRepo.findById(pcost);
        if(parkingSpot.isPresent()){
            return new ResponseEntity<>(parkingSpot.get(), HttpStatus.OK);
        }
        else{
            System.out.println("HIII honey");
            throw new ParkingSpotNotFoundException(pcost);
        }
    }


}
