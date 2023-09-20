package com.example.helloworld.controller;

import com.example.helloworld.Exception.IssuedParkingSpotsNotFoundException;
import com.example.helloworld.Repositoy.IssuedParkingSpotsRepo;
import com.example.helloworld.model.IssuedParkingSpots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IssuedParkingSpotsController {

    @Autowired
    IssuedParkingSpotsRepo IssueRepo;

    @GetMapping("/api/issuedParkingSpots")
    List<IssuedParkingSpots> findAll(){
        return IssueRepo.findAll();
    }

    @PostMapping("/api/issuedParkingSpots")
    public ResponseEntity<IssuedParkingSpots> saveIssuedParkingspots(@RequestBody IssuedParkingSpots issuedParkingSpots) throws Exception{
        try{
            issuedParkingSpots.setStatus("Issued");
            return new ResponseEntity<>(IssueRepo.save(issuedParkingSpots), HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    @GetMapping("/api/issuedParkingSpots{user_id}")
    public ResponseEntity<IssuedParkingSpots> getIssuedParkingSpotsByUId(@PathVariable long user_id){
        Optional<IssuedParkingSpots> issuedParkingSpots = IssueRepo.findById(user_id);
        if(issuedParkingSpots.isPresent()){
            return new ResponseEntity<>(issuedParkingSpots.get(), HttpStatus.OK);
        }
        else{
            throw new IssuedParkingSpotsNotFoundException(user_id);
        }
    }

    @GetMapping("/api/issuedParkingSpots{status}")
    public ResponseEntity<IssuedParkingSpots> getIssuedParkingSpotsByPId(@PathVariable long parking_id){
        Optional<IssuedParkingSpots> issuedParkingSpots = IssueRepo.findById(parking_id);
        if(issuedParkingSpots.isPresent()){
            return new ResponseEntity<>(issuedParkingSpots.get(), HttpStatus.OK);
        }
        else{
            throw new IssuedParkingSpotsNotFoundException(parking_id);
        }
    }

}
